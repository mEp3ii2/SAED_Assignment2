package edu.curtin.maze_game.core_game;

//import edu.curtin.maze_game.lib.Direction;
import edu.curtin.maze_game.lib.*;



import java.util.Map;
import java.util.List;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import edu.curtin.maze_game.api.API;
import edu.curtin.maze_game.api.EventManager;
import edu.curtin.maze_game.api.ItemAcquisitionListener;
import edu.curtin.maze_game.api.PlayerMovementListener;
import edu.curtin.maze_game.api.PluginActionListener;
import edu.curtin.maze_game.dsl.MazeGameParser;
import edu.curtin.maze_game.dsl.ParseException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) 
    {
        
        
        if(args.length==0){
            System.out.println("Where file? huh");
            return;
        } 
        
        String file = args[0];
        String encode = determineEncode(file);
        if(encode == null){
            System.out.println("Invalid file name. Please use input.utf8.map or input.utf16.map or input.utf32.map");
            return;
        }

        SwingUtilities.invokeLater(()->start(file,encode)); // Equivalent to JavaFX's Platform.runLater().   
    }

    private static String determineEncode(String filename) {
        if (filename.contains(".utf8.")) {
            return "UTF-8";
        } else if (filename.contains(".utf16.")) {
            return "UTF-16";
        } else if (filename.contains(".utf32.")) {
            return "UTF-32";
        }
        return null;
    }

    public static void start(String file, String encode)
    {
        var window = new JFrame(LocaleManager.getBundle().getString("game.title"));
        
        GameContext gameState = new GameContext();
        EventManager eventManager = new EventManager();
        ApiController gameAPI = new ApiController(gameState);
        
        try{
            MazeGameParser parser;
            parser = MazeGameParser.parse(file,encode);
            
            // get game deets from parser
            int[] boardDimensions = parser.getSize();            
            gameState.setArea(new GridArea((double)boardDimensions[0], (double)boardDimensions[1]));
            gameState.getArea().setBackground(new Color(186, 151, 24));


            //player init
            int[] playerStart = parser.getStartPosition();
            setPlayer(playerStart[0],playerStart[1],gameState);
            //goal init
            int[] goalLocation = parser.getGoal();
            setGoal(goalLocation[0], goalLocation[1], gameState);
            
            //item init
            Map<Cord,Tuple<String,String>> parserItems = parser.getItemDetails();
            parserItems.forEach((position, itemDetails) -> {
                String itemName = itemDetails.getFirst();
                String itemDescription = itemDetails.getSecond();
                GameItem item = (GameItem) GameEntityFactory.createGameEntity("item", 
                                                                            itemName, 
                                                                            itemDescription, 
                                                                            position.getX(), 
                                                                            position.getY(),
                                                                            boardDimensions[0],
                                                                            boardDimensions[1]);
                gameState.getEntities().put(item.getPosition(), item); 
                gameState.getArea().getIcons().add(item.getIcon());
            });

            //obstacle init
            Map<Cord,String> parserObstacles = parser.getObstacleDetails();
            parserObstacles.forEach((position,requires)->{
                Obstacle obstacle = (Obstacle) GameEntityFactory.createGameEntity("obstacle", 
                                                                                "Obstacle", 
                                                                                requires,
                                                                                position.getX(), 
                                                                                position.getY(),
                                                                                boardDimensions[0],
                                                                                boardDimensions[1]);

            gameState.getEntities().put(obstacle.getPosition(), obstacle); 
            gameState.getArea().getIcons().add(obstacle.getIcon());
            });

            //plugin init
            List<String> pluginList = parser.getPlugins();
            
        
            
            for(String pluginName: pluginList){
                try{
                    Class<?> pluginClass = Class.forName(pluginName);

                    Object pluginInstance = pluginClass.getDeclaredConstructor(API.class).newInstance(gameAPI);
                    if (pluginInstance instanceof PlayerMovementListener) {
                        eventManager.registerMovementListener((PlayerMovementListener) pluginInstance);
                    }
                    if (pluginInstance instanceof ItemAcquisitionListener) {
                        System.out.println("register it ");
                        eventManager.registerItemListener((ItemAcquisitionListener) pluginInstance);
                    }
                    if (pluginInstance instanceof PluginActionListener) {
                        eventManager.registerPluginListener((PluginActionListener) pluginInstance);
                        
                    }
                }catch(ClassNotFoundException e){
                    System.out.println("Plugin class not found: "+pluginName);
                }catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) { // gotta catch em all
                    System.out.println("Failed to load plugin: " + pluginName + " - " + e.getMessage());
                }
            }

            //script init
            

            //fog init
            LoadFog.loadFogOfWar(boardDimensions, gameState.getFogOfWar(),gameState.getArea(),gameState.getPlayer().getPosition());
            
        }catch (IOException e){
            System.out.println("IO excpetion occured: "+e.getMessage());
        }catch (ParseException e){
            System.out.println("Parse exception occured: "+e.getMessage());
        }
    
        
        

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent e)
            {
                System.out.println("Window closed");
            }
        });

        var statusText = new JLabel(LocaleManager.getBundle().getString("game.status"));
        var textArea = new JTextArea();
        
        String dateFormat = LocaleManager.getBundle().getString("game.date");
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate currDate = LocalDate.now();

        String formatDate = currDate.format(format);

        var dateText = new JLabel(formatDate);
        JButton pluginButton = new JButton(LocaleManager.getBundle().getString("game.plugin"));
        
        pluginButton.addActionListener(e ->{
            eventManager.notifyPlugin();
            pluginButton.setEnabled(false);
        });
        
        JButton changeLocaleButton = new JButton(LocaleManager.getBundle().getString("game.change_locale"));
        changeLocaleButton.addActionListener(e->{
            LocaleManager.changeLocal();
            window.setTitle(LocaleManager.getBundle().getString("game.title"));
            statusText.setText(LocaleManager.getBundle().getString("game.status"));    
            updateInvTextArea(gameState.getPlayer(), textArea);
            changeLocaleButton.setText(LocaleManager.getBundle().getString("game.change_locale"));
            pluginButton.setText(LocaleManager.getBundle().getString("game.plugin"));
            String newDateFormat = LocaleManager.getBundle().getString("game.date");
            DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern(newDateFormat);
            LocalDate currDate2 = LocalDate.now();
            String formattedNewDate = currDate2.format(newFormatter);
    
    
            dateText.setText(formattedNewDate);
        });

        
        updateInvTextArea(gameState.getPlayer(), textArea);
        
        JPanel moveBtnPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JButton upButton = new JButton("^");
        constraints.gridx = 1;
        constraints.gridy = 0;
        moveBtnPanel.add(upButton,constraints);
        upButton.addActionListener(e->{
            handleMove(Direction.UP, gameState, dateText, textArea, eventManager);
        });

        JButton downButton = new JButton("V");
        constraints.gridx = 1;
        constraints.gridy = 2;
        moveBtnPanel.add(downButton,constraints);
        downButton.addActionListener(e->{
            handleMove(Direction.DOWN, gameState, dateText, textArea, eventManager);
        });

        JButton leftButton = new JButton("<");
        constraints.gridx = 0;
        constraints.gridy = 1;
        moveBtnPanel.add(leftButton,constraints);
        leftButton.addActionListener(e->{
            handleMove(Direction.LEFT, gameState, dateText, textArea, eventManager);
        });

        JButton rightButton = new JButton(">");
        constraints.gridx = 2;
        constraints.gridy = 1;
        moveBtnPanel.add(rightButton,constraints);
        rightButton.addActionListener(e -> {
            handleMove(Direction.RIGHT, gameState, dateText, textArea, eventManager);
        });
        
        
        
        
        var toolbar = new JToolBar();
        toolbar.add(changeLocaleButton);
        toolbar.addSeparator();
        toolbar.add(statusText);
        toolbar.addSeparator();
        toolbar.add(dateText);
        toolbar.addSeparator();
        toolbar.add(pluginButton);

        

        var scrollingTextArea = new JScrollPane(textArea);
        scrollingTextArea.setBorder(BorderFactory.createEtchedBorder());

        var splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT, gameState.getArea(), scrollingTextArea);

        Container contentPane = window.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(toolbar, BorderLayout.NORTH);
        contentPane.add(splitPane, BorderLayout.CENTER);
        contentPane.add(moveBtnPanel,BorderLayout.SOUTH);

        window.setPreferredSize(new Dimension(1200, 1000));
        window.pack();
        splitPane.setDividerLocation(0.75);
        window.setVisible(true);
    }

    
    public static void updateInvTextArea(Player player, JTextArea textArea){
        textArea.setText("");
        textArea.append(LocaleManager.getBundle().getString("inventory.header")+"\n");
        textArea.append(player.getInventory());
    }

    public static void incrementDate(JLabel date, GameContext context){
        context.incrementDate();
        LocalDate upDate = LocalDate.now().plusDays(context.getCounter());
        String newDateFormat = LocaleManager.getBundle().getString("game.date");
        DateTimeFormatter format = DateTimeFormatter.ofPattern(newDateFormat);
        String formDate = upDate.format(format);
        date.setText(formDate);
        context.getGoal().incrementDay();
    }

    private static void setGoal(int goalX, int goalY, GameContext context){
        Cord goalPosition = new Cord(goalX, goalY);

        GridAreaIcon goalIcon = new GridAreaIcon(
        goalPosition.getX(), goalPosition.getY(), 0, 1.0,
        App.class.getClassLoader().getResource("doorIcon.png"),
        "");
        goalIcon.setShown(false);

        context.getArea().getIcons().add(goalIcon);
        
        Goal goalEntity = new Goal("Goal", LocaleManager.getBundle().getString("goal.ui"), goalPosition, goalIcon);
        context.setGoal(goalEntity);
        context.getEntities().put(goalPosition, goalEntity);
    }

    private static void setPlayer(int playerX, int playerY, GameContext context){
        Cord start = new Cord(playerX, playerY);
    
        GridAreaIcon playerIcon = new GridAreaIcon(
            start.getX(),     
            start.getY(), 
            0, 
            1, 
            App.class.getClassLoader().getResource("playerIcon.png"), 
            "");
        context.getArea().getIcons().add(playerIcon);            
        Player player = new Player(start, playerIcon);
        context.setPlayer(player);
    }

    private static void handleMove(Direction direction, GameContext context, JLabel dateText, JTextArea textArea, EventManager eventManager){
        
        int[] mapSize = new int[]{ (int)context.getArea().getGridWidth(),(int)context.getArea().getGridHeight()};
        boolean moved = MoveHandler.movePlayer(mapSize, direction, context, eventManager);
        if(moved){
            DisplayHandler.updateSurrondings(context.getEntities(),context.getFogOfWar(),context.getPlayer());
            context.getArea().repaint();
            updateInvTextArea(context.getPlayer(), textArea);
            incrementDate(dateText, context);
            eventManager.notifyPlayerMove();
        }
    }
}
