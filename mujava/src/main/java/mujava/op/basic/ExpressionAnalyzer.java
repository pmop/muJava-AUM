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
        Debug.println("\nsetContainsZeroLiteralIfTrue");
        if (exp instanceof Literal) {
            Debug.println("Expression is Literal >>>> " + exp.toString());
            if (exp.toString().equals("0")) {
                containsZeroLiteral = true;
                Debug.println("containsZeroLiteral = true");
            }
            else {
                Debug.println("containsZeroLiteral = false");
            }
        }
        else {
            Debug.println("Expression is not Literal");
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
        System.out.println("\nInsideIf: " + insideIf);
    }

    private void setInsideFor(boolean insideFor) {
        this.insideFor = insideFor;
        Debug.println3("\nInsideFor: " + insideFor);
    }

    private void setContainsString(Expression exp) {
        System.out.println("\nsetContainsString >>>> " + exp);
        try {
            OJClass ojc = null;
            if (exp instanceof MethodCall) {
                System.out.println("Exp is a method call so i'll check using MethodCall properties");
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
                System.out.println("Is String");
                this.containsString = true;
            }
            else {
                System.out.println("Is not String");
            }
        } catch (Exception e) {
            System.out.println("Cannot parse. Probably not a string.\nReason: " + e.getMessage());
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
        System.out.println("\nsetContainsArray >>>> " + expression.toString());
        try {
            OJClass ojc = expression.getType(environment);
            if (ojc.isArray()) {
                this.containsArray = true;
                System.out.println("Is array");
            } else {
                System.out.println("Is not array");
            }
        } catch (Exception e) {
            System.out.println("Cannot parse expression.");
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
        System.out.println("right: " + right);
    }

    private void setLeft(Expression left) {
        this.left = left;
        System.out.println("left: " + left);
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
        System.out.println("\nsetContainsMethodCallIfTrue");
        if (exp instanceof MethodCall) {
            MethodCall c = (MethodCall) exp;
            System.out.println("Is a method call. >>>> " + exp.toString());
            if (c.getName().equals("length")) {
                containsLengthMethodCall = true;
                System.out.println("Is length method call.");
            }
            else {
                System.out.println("Is not length method call. >>>> " + c.getName());
            }
        }
        else {
            System.out.println("Not a method call. >>>> " + exp.toString());
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
            System.out.println("Parsing as BinaryExpression");
            setBinaryExpressionProperties((BinaryExpression) rootExpression);
        }
        else if (rootExpression instanceof ForStatement) {
            System.out.println("Parsing as ForStatement");
            setForStatementProperties((ForStatement) rootExpression);
        } else {
            System.out.println("Expression parsing not implemented for: ");
        }

    }

    private ExpressionAnalyzer() {
        init();
    }

    public ExpressionAnalyzer(Expression expression, Environment environment) {
        this();
        rootExpression = expression;
        this.environment = environment;
        System.out.println("\nAnalyzing expression: " + expression.toString());
        parse();
    }
    public ExpressionAnalyzer(BinaryExpression expression, Environment environment) {
        this((Expression) expression, environment);
    }
}
