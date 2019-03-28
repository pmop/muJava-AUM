package mujava.op.basic;

import mujava.util.Debug;
import openjava.mop.Environment;
import openjava.mop.OJClass;
import openjava.mop.OJSystem;
import openjava.ptree.*;
import openjava.tools.parser.ParseException;


public class ExpressionAnalyzer {
    private Expression rootExpression;
    private Environment environment;
    private boolean insideIf;
    private boolean insideFor;
    private boolean containsZeroLiteral;
    private boolean containsString;
    private boolean forIteratorStartsAtZero;
    private boolean forIteratorIncrements;
    private boolean containsArray;
    private boolean containsLengthMethodCall;
    private boolean containsBinaryOperator;
    private BinaryOperator rootOperator;
    private Expression right;
    private Expression left;

    public static boolean DEBUG = true;

    private final void Debug(String str) {
        if (DEBUG) System.out.println(str);
    }

    public BinaryExpression getRootExpression() {
        return (BinaryExpression) rootExpression;
    }

    public boolean isInsideIf() {
        return insideIf;
    }

    public boolean isInsideFor() {
        return insideFor;
    }

    public boolean setContainsZeroLiteralIfTrue(Expression exp) {
        Debug("\nsetContainsZeroLiteralIfTrue >>>>> " + exp.toString() );
        if (exp instanceof Literal) {
            Debug("Expression is Literal.");
            if (exp.toString().equals("0")) {
                containsZeroLiteral = true;
                Debug("Expression is zero literal");
            }
            else {
                Debug("containsZeroLiteral = false");
            }
        }
        else {
            Debug("Expression is not Literal");
        }
        return containsZeroLiteral;
    }

    public boolean containsString() {
        return containsString;
    }

    public boolean containsArray() {
        return containsArray;
    }

    public boolean containsLengthMethodCall() {
        return containsLengthMethodCall;
    }

    public boolean containsBinaryOperator() {
        return containsBinaryOperator;
    }

    public boolean isContainsZeroLiteral() {
        return containsZeroLiteral;
    }

    public BinaryOperator getRootOperator() {
        return rootOperator;
    }

    public Expression getRight() {
        return right;
    }

    public Expression getLeft() {
        return left;
    }
    public boolean isForIteratorStartsAtZero() {
        return forIteratorStartsAtZero;
    }

    public boolean isForIteratorIncrements() {
        return forIteratorIncrements;
    }

    private void setRootExpression(BinaryExpression rootExpression) {
        this.rootExpression = rootExpression;
    }

    private void setEnvironment(Environment environment) {
        this.environment = environment;
        Debug.println3("Environment was set.");
    }

    private void setInsideIf(boolean insideIf) {
        this.insideIf = insideIf;
        Debug("\nInsideIf: " + insideIf);
    }

    private void setInsideFor(boolean insideFor) {
        this.insideFor = insideFor;
        Debug.println3("\nInsideFor: " + insideFor);
    }

    private void setContainsString(Expression exp) {
        Debug("\nsetContainsString >>>> " + exp);
        try {
            OJClass ojc = null;
            if (exp instanceof MethodCall) {
                Debug("Exp is a method call so i'll check using MethodCall properties");
                Object[] contents = ((MethodCall) exp).getContents();
                Variable rootVariable = null;
                if (contents != null &&  contents[0] != null) {
                    rootVariable = (Variable) contents[0];
                    ojc = rootVariable.getType(this.environment);
                }
            }
            else {
                ojc = exp.getType(this.environment);
            }
            if (ojc == OJSystem.STRING) {
                Debug("Is String");
                this.containsString = true;
            }
            else {
                Debug("Is not String");
            }
        } catch (Exception e) {
            Debug("Cannot parse. Probably not a string.\nReason: " + e.getMessage());
        }
    }

    private void setForIteratorStartsAtZero(boolean forIteratorStartsAtZero) {
        this.forIteratorStartsAtZero = forIteratorStartsAtZero;
        Debug.println3("forIteratorStartsAtZero: " + forIteratorStartsAtZero);
    }

    private void setForIteratorIncrements(boolean forIteratorIncrements) {
        this.forIteratorIncrements = forIteratorIncrements;
        Debug.println3("forIteratorIncrements: " + forIteratorIncrements);
    }

    private void setContainsArray(Expression expression) {
        Debug("\nsetContainsArray >>>> " + expression.toString());
        try {
            OJClass ojc = null;
            if (expression instanceof MethodCall) {
                Debug("Expression is a method call so i'll check using MethodCall properties");
                Object[] contents = ((MethodCall) exp).getContents();
                Variable rootVariable = null;
                if (contents != null &&  contents[0] != null) {
                    rootVariable = (Variable) contents[0];
                    ojc = rootVariable.getType(this.environment);
                }
            }
            else {
                ojc = expression.getType(environment);
            }
            if (ojc.isArray()) {
                this.containsArray = true;
                Debug("Is array");
            }
            else {
                Debug("Is not array");
            }
        }
        catch (Exception e) {
            Debug("Cannot parse expression. Probably not a string.\nReason: " + e.getMessage());
        }
    }

    private void setContainsLengthMethodCall(boolean containsLengthMethodCall) {
        this.containsLengthMethodCall = containsLengthMethodCall;
        Debug.println3("containsLengthMethodCall: " + containsLengthMethodCall);
    }

    private void setContainsBinaryOperator(boolean containsBinaryOperator) {
        this.containsBinaryOperator = containsBinaryOperator;
        Debug.println3("containsBinaryOperator: " + containsBinaryOperator);
    }

    private void setRootOperator(BinaryOperator rootOperator) {
        this.rootOperator = rootOperator;
    }

    private void setRight(Expression right) {
        this.right = right;
        Debug("right: " + right);
    }

    private void setLeft(Expression left) {
        this.left = left;
        Debug("left: " + left);
    }

    public boolean containsZeroLiteral() {
        return this.containsZeroLiteral;
    }

    public enum BinaryOperator {
        NONE {
            @Override
            public String toString() {
                return "None";
            }
        },
        PLUS {
            @Override
            public String toString() {
                return "+";
            }
        },
        MINUS {
            @Override
            public String toString() {
                return "-";
            }
        },
        EQUALS {
            @Override
            public String toString() {
                return "==";
            }
        },
        DIFFERENT {
            @Override
            public String toString() {
                return "!=";
            }
        },
        GREATER {
            @Override
            public String toString() {
                return ">";
            }
        },
        LESSER {
            @Override
            public String toString() {
                return "<";
            }
        },
        GREATEREQUAL {
            @Override
            public String toString() {
                return ">=";
            }
        },
        LESSEREQUAL {
            @Override
            public String toString() {
                return "<=";
            }
        },
        NOTIMPLEMENTED {
            @Override
            public String toString() {
                return "Not implemented";
            }
        }
    }

    BinaryOperator translateFromBinaryExpression(int binaryExpressionOperator) {
        BinaryOperator op;
        switch (binaryExpressionOperator) {
            case BinaryExpression.PLUS:
                op = BinaryOperator.PLUS;
                break;
            case BinaryExpression.MINUS:
                op = BinaryOperator.MINUS;
                break;
            case BinaryExpression.EQUAL:
                op = BinaryOperator.EQUALS;
                break;
            case BinaryExpression.NOTEQUAL:
                op = BinaryOperator.DIFFERENT;
                break;
            case BinaryExpression.GREATER:
                op = BinaryOperator.GREATER;
                break;
            case BinaryExpression.LESS:
                op = BinaryOperator.LESSER;
                break;
            case BinaryExpression.GREATEREQUAL:
                op = BinaryOperator.GREATEREQUAL;
                break;
            case BinaryExpression.LESSEQUAL:
                op = BinaryOperator.LESSEREQUAL;
                break;
            default:
                op = BinaryOperator.NOTIMPLEMENTED;
                break;
        }
        return op;
    }

    void init() {
        rootOperator = BinaryOperator.NONE;
        rootExpression = null;
        environment = null;
        insideIf = false;
        insideFor = false;
        containsZeroLiteral = false;
        containsString = false;
        containsArray = false;
        containsLengthMethodCall = false;
        containsBinaryOperator = false;
        forIteratorIncrements = false;
        forIteratorStartsAtZero = false;
        right = null;
        left = null;
    }

    private void setContainsMethodCallIfTrue(Expression exp) {
        Debug("\nsetContainsMethodCallIfTrue");
        if (exp instanceof MethodCall) {
            MethodCall c = (MethodCall) exp;
            Debug("Is a method call. >>>> " + exp.toString());
            if (c.getName().equals("length")) {
                containsLengthMethodCall = true;
                Debug("Is length method call.");
            }
            else {
                Debug("Is not length method call. >>>> " + c.getName());
            }
        }
        else {
            Debug("Not a method call. >>>> " + exp.toString());
        }
    }

    private void setForStatementProperties(ForStatement exp) {
        Debug.println3("Expression is ForStatement: " + exp.toString());
        setInsideFor(true);
    }

    private void setBinaryExpressionProperties(BinaryExpression exp) {
        ParseTreeObject pto = exp;

        setInsideIf(pto.getParent() instanceof IfStatement);
        setRootOperator(translateFromBinaryExpression(exp.getOperator()));
        setRight(exp.getRight());
        setLeft(exp.getLeft());
        setContainsMethodCallIfTrue(right);
        setContainsZeroLiteralIfTrue(right);
        setContainsMethodCallIfTrue(left);
        setContainsZeroLiteralIfTrue(left);
        setContainsArray(left);
        setContainsArray(right);
        setContainsString(left);
        setContainsString(right);

    }

    void parse() {
        if (rootExpression instanceof BinaryExpression) {
            Debug("Parsing as BinaryExpression");
            setBinaryExpressionProperties((BinaryExpression) rootExpression);
        }
        else if (rootExpression instanceof ForStatement) {
            Debug("Parsing as ForStatement");
            setForStatementProperties((ForStatement) rootExpression);
        } else {
            Debug("Expression parsing not implemented for: ");
        }

    }

    private ExpressionAnalyzer() {
        init();
    }

    public ExpressionAnalyzer(Expression expression, Environment environment) {
        this();
        rootExpression = expression;
        this.environment = environment;
        Debug("\nAnalyzing expression: " + expression.toString());
        parse();
    }
    public ExpressionAnalyzer(BinaryExpression expression, Environment environment) {
        this((Expression) expression, environment);
    }
}
