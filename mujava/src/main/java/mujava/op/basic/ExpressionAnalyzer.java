package mujava.op.basic;

import openjava.mop.Environment;
import openjava.mop.OJClass;
import openjava.ptree.*;
import openjava.tools.parser.ParseException;


public class ExpressionAnalyzer {
    private BinaryExpression rootExpression;
    private Environment environment;
    private boolean insideIf;
    private boolean insideFor;
    private boolean containsZeroLiteral;
    private boolean containsString;
    private boolean forIteratorStartsAtZero;
    private boolean forIteratorIncrements;

    public BinaryExpression getRootExpression() {
        return rootExpression;
    }

    public boolean isInsideIf() {
        return insideIf;
    }

    public boolean isInsideFor() {
        return insideFor;
    }

    public boolean containsZeroLiteral() {
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

    public BinaryOperator getRootOperator() {
        return rootOperator;
    }

    public Expression getRight() {
        return right;
    }

    public Expression getLeft() {
        return left;
    }

    boolean containsArray;
    boolean containsLengthMethodCall;
    boolean containsBinaryOperator;
    BinaryOperator rootOperator;
    Expression right;
    Expression left;

    public boolean isForIteratorStartsAtZero() {
        return forIteratorStartsAtZero;
    }

    public boolean isForIteratorIncrements() {
        return forIteratorIncrements;
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

    void parse() throws Exception {
        insideIf = rootExpression.getParent() instanceof IfStatement;
        insideFor = rootExpression.getParent() instanceof ForStatement;
        rootOperator = translateFromBinaryExpression(rootExpression.getOperator());

        right = rootExpression.getRight();
        left = rootExpression.getLeft();

        OJClass ojcRight = right.getType(environment);
        OJClass ojcLeft = left.getType(environment);

        if (ojcRight.isArray() || ojcLeft.isArray()) {
            this.containsArray = true;
        }


//        if (ojcr.isPrimitive() && ojcr.) {
//            this.containsArray
//        }

//        containsZeroLiteral = (right instanceof Variable) && ((Variable) right.toString() == '0' );

    }

    private ExpressionAnalyzer() {
        init();
    }

    public ExpressionAnalyzer(BinaryExpression expression, Environment environment) throws Exception {
        this();
        rootExpression = expression;
        this.environment = environment;
        parse();
    }
}
