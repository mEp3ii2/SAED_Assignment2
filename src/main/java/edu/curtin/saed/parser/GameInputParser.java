package edu.curtin.saed.parser;
/* Generated By:JavaCC: Do not edit this line. GameInputParser.java */
public class GameInputParser implements GameInputParserConstants {
    public static void main(String[] args) throws ParseException {
        GameInputParser parser = new GameInputParser(System.in);
        parser.InputFile();
    }

  static final public void InputFile() throws ParseException {
    Declaration();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ITEM:
      case OBSTACLE:
      case PLUGIN:
      case SCRIPT:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ITEM:
        ItemDeclaration();
        break;
      case OBSTACLE:
        ObstacleDeclaration();
        break;
      case PLUGIN:
        PluginDeclaration();
        break;
      case SCRIPT:
        ScriptDeclaration();
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void Declaration() throws ParseException {
    SizeDeclaration();
    StartDeclaration();
    GoalDeclaration();
  }

  static final public void SizeDeclaration() throws ParseException {
    jj_consume_token(SIZE);
    jj_consume_token(LPAREN);
    RowColumn();
    jj_consume_token(RPAREN);
  }

  static final public void StartDeclaration() throws ParseException {
    jj_consume_token(START);
    jj_consume_token(LPAREN);
    RowColumn();
    jj_consume_token(RPAREN);
  }

  static final public void GoalDeclaration() throws ParseException {
    jj_consume_token(GOAL);
    jj_consume_token(LPAREN);
    RowColumn();
    jj_consume_token(RPAREN);
  }

  static final public void RowColumn() throws ParseException {
    jj_consume_token(DIGIT);
    jj_consume_token(COMMA);
    jj_consume_token(DIGIT);
  }

  static final public void ItemDeclaration() throws ParseException {
    jj_consume_token(ITEM);
    jj_consume_token(STRING);
    jj_consume_token(LBRACE);
    AtDeclaration();
    MessageDeclaration();
    jj_consume_token(RBRACE);
  }

  static final public void ObstacleDeclaration() throws ParseException {
    jj_consume_token(OBSTACLE);
    jj_consume_token(LBRACE);
    AtDeclaration();
    RequiresDeclaration();
    jj_consume_token(RBRACE);
  }

  static final public void PluginDeclaration() throws ParseException {
    jj_consume_token(PLUGIN);
    JavaClass();
  }

  static final public void ScriptDeclaration() throws ParseException {
    jj_consume_token(SCRIPT);
    jj_consume_token(EXCLAMATION_BRACE);
    ScriptCode();
    jj_consume_token(RBRACE);
  }

  static final public void AtDeclaration() throws ParseException {
    jj_consume_token(AT);
    jj_consume_token(LPAREN);
    RowColumn();
    jj_consume_token(RPAREN);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      jj_consume_token(LPAREN);
      RowColumn();
      jj_consume_token(RPAREN);
    }
  }

  static final public void MessageDeclaration() throws ParseException {
    jj_consume_token(MESSAGE);
    jj_consume_token(STRING);
  }

  static final public void RequiresDeclaration() throws ParseException {
    jj_consume_token(REQUIRES);
    jj_consume_token(STRING);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      jj_consume_token(COMMA);
      jj_consume_token(STRING);
    }
  }

  static final public void JavaClass() throws ParseException {
    label_4:
    while (true) {
      jj_consume_token(24);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 24:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_4;
      }
    }
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 25:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_5;
      }
      jj_consume_token(25);
      label_6:
      while (true) {
        jj_consume_token(26);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 26:
          ;
          break;
        default:
          jj_la1[6] = jj_gen;
          break label_6;
        }
      }
    }
  }

  static final public void ScriptCode() throws ParseException {
    jj_consume_token(SCRIPT);
    jj_consume_token(EXCLAMATION_BRACE);
    jj_consume_token(STRING);
    jj_consume_token(RBRACE);
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public GameInputParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[7];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x630,0x630,0x10000,0x10000,0x1000000,0x2000000,0x4000000,};
   }

  /** Constructor with InputStream. */
  public GameInputParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public GameInputParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new GameInputParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public GameInputParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new GameInputParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public GameInputParser(GameInputParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(GameInputParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[27];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 7; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 27; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}