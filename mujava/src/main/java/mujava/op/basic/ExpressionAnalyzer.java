package mujava.op.basic;

import mujava.util.Debug;
import openjava.mop.Environment;
import openjava.mop.OJClass;
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
        if (exp instanceof Literal) {
            Literal l = (Literal) exp;
            if (l.getLiteralType() == 0) {
                containsZeroLiteral = true;
            }
        }
        Debug.println("Contains zero literal is: " + containsZeroLiteral);
        Debug.println("[Contains zero] expression is: " + exp.toString());
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
        Debug.println3("Root expression was set : " + rootExpression.toString());
    }

    private void setEnvironment(Environment environment) {
        this.environment = environment;
        Debug.println3("Environment was set.");
    }

    private void setInsideIf(boolean insideIf) {
        this.insideIf = insideIf;
        Debug.println3("InsideIf: " + insideIf);
    }

    private void setInsideFor(boolean insideFor) {
        this.insideFor = insideFor;
        Debug.println3("InsideFor: " + insideFor);
    }

    private void setContainsZeroLiteral(boolean containsZeroLiteral) {
        this.containsZeroLiteral = containsZeroLiteral;
        Debug.println3("containsZeroLiteral: " + containsZeroLiteral);
    }

    private void setContainsString(boolean containsString) {
        this.containsString = containsString;
        Debug.println3("containsString: " + containsString);
    }

    private void setForIteratorStartsAtZero(boolean forIteratorStartsAtZero) {
        this.forIteratorStartsAtZero = forIteratorStartsAtZero;
        Debug.println3("forIteratorStartsAtZero: " + forIteratorStartsAtZero);
    }

    private void setForIteratorIncrements(boolean forIteratorIncrements) {
        this.forIteratorIncrements = forIteratorIncrements;
        Debug.println3("forIteratorIncrements: " + forIteratorIncrements);
    }

    private void setContainsArray(boolean containsArray) {
        this.containsArray = containsArray;
        Debug.println3("containsArray: " + containsArray);
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
        Debug.println3("rootOperator: " + rootOperator);
    }

    private void setRight(Expression right) {
        this.right = right;
        Debug.println3("right: " + right);
    }

    private void setLeft(Expression left) {
        this.left = left;
        Debug.println3("left: " + left);
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
        if (exp instanceof MethodCall) {
            MethodCall c = (MethodCall) exp;
            if (c.getName().equals("length")) {
                containsLengthMethodCall = true;
                Debug.println3("containsLengthMethodCall: true.");
            }
        }
    }

    private void setForStatementProperties(ForStatement exp) {
        Debug.println3("Expression is ForStatement: " + exp.toString());
        setInsideFor(true);
    }

    private void setBinaryExpressionProperties(BinaryExpression exp) {
        Debug.println3("Expression is BinaryExpression: " + exp.toString());
        ParseTreeObject pto = exp;

        setInsideIf(pto.getParent() instanceof IfStatement);
        setRootOperator(translateFromBinaryExpression(exp.getOperator()));
        setRight(exp.getRight());
        setLeft(exp.getLeft());
        setContainsMethodCallIfTrue(right);
        setContainsZeroLiteralIfTrue(right);
        setContainsMethodCallIfTrue(left);
        setContainsZeroLiteralIfTrue(left);

        try {
            OJClass ojcRight = right.getType(environment);
            OJClass ojcLeft = left.getType(environment);

            if (ojcRight.isArray() || ojcLeft.isArray()) {
                setContainsArray(true);
            }

            if ((ojcLeft.getClass().getName().equals("String")) ||
                    (ojcRight.getClass().getName().equals("String")) ) {
                setContainsString(true);
            }

        } catch (Exception e) {
            Debug.println("Unable to parse left and right expressions from BinaryExpression: " + exp.toString());
        }
    }

    void parse() {
        if (rootExpression instanceof BinaryExpression) {
            setBinaryExpressionProperties((BinaryExpression) rootExpression);
        }
        else if (rootExpression instanceof ForStatement) {
            setForStatementProperties((ForStatement) rootExpression);
        } else {
            Debug.println3("Expression parsing not implemented for: "
                    + rootExpression.toString());
        }

    }

    private ExpressionAnalyzer() {
        init();
    }

    public ExpressionAnalyzer(Expression expression, Environment environment) {
        this();
        rootExpression = expression;
        this.environment = environment;
        parse();
    }
    public ExpressionAnalyzer(BinaryExpression expression, Environment environment) {
        this((Expression) expression, environment);
    }
}
