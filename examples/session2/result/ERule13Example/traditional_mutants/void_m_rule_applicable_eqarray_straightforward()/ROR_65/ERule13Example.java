// This is a mutant program.
// Author : ysma

import java.util.Collection;
import java.util.Map;
import java.util.Set;


class ERule13Example
{

    public ERule13Example()
    {
    }

    private class GenericContainerMock
    {

        public  int length()
        {
            return 6;
        }

        public  int size()
        {
            return length();
        }

    }

     void defaultMethodCall()
    {
    }

     void m_rule_applicable_gtstring_straightforward()
    {
        java.lang.String string = "string";
        if (string.length() > 0) {
            defaultMethodCall();
        }
    }

     void m_rule_applicable_gtstring_reverseorder()
    {
        java.lang.String string = "string";
        if (0 < string.length()) {
            defaultMethodCall();
        }
    }

     void m_rule_applicable_eqstring_reverseorder()
    {
        java.lang.String string = "string";
        if (0 == string.length()) {
            defaultMethodCall();
        }
    }

     void m_rule_applicable_eqstring_straightforward()
    {
        java.lang.String string = "string";
        if (string.length() == 0) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_neqstring_straightforward()
    {
        java.lang.String string = "string";
        if (string.length() != 0) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_neqstring_reverseorder()
    {
        java.lang.String string = "string";
        if (0 != string.length()) {
            defaultMethodCall();
        }
    }

     void m_rule_applicable_gtarray_straightforward()
    {
        int[] array = { 1, 2, 3 };
        if (array.length > 0) {
            defaultMethodCall();
        }
    }

     void m_rule_applicable_gtarray_reverseorder()
    {
        int[] array = { 1, 2, 3 };
        if (0 < array.length) {
            defaultMethodCall();
        }
    }

     void m_rule_applicable_eqarray_reverseorder()
    {
        int[] array = { 1, 2, 3 };
        if (0 == array.length) {
            defaultMethodCall();
        }
    }

     void m_rule_applicable_eqarray_straightforward()
    {
        int[] array = { 1, 2, 3 };
        if (array.length >= 0) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_neqarray_straightforward()
    {
        int[] array = { 1, 2, 3 };
        if (array.length != 0) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_neqarray_reverseorder()
    {
        int[] array = { 1, 2, 3 };
        if (0 != array.length) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_1()
    {
        java.lang.String string = "string";
        if (string.length() > 1) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_2()
    {
        java.lang.String string = "string";
        if (string.length() != 1) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_3()
    {
        java.lang.String string = "string";
        if (string.length() < 6) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_4()
    {
        java.lang.String string = "string";
        if (string.length() < 7) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_5()
    {
        java.lang.String string = "string";
        if (string.length() < 7) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_6()
    {
        int[] array = { 1, 2, 3, 4, 5, 6 };
        if (array.length > 1) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_7()
    {
        int[] array = { 1, 2, 3, 4, 5, 6 };
        if (array.length != 1) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_8()
    {
        int[] array = { 1, 2, 3, 4, 5, 6 };
        if (array.length < 6) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_9()
    {
        int[] array = { 1, 2, 3, 4, 5, 6 };
        if (array.length < 7) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable_10()
    {
        int[] array = { 1, 2, 3, 4, 5, 6 };
        if (array.length < 7) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable11()
    {
        ERule13Example.GenericContainerMock gcm = new ERule13Example.GenericContainerMock();
        if (gcm.length() > 0) {
            defaultMethodCall();
        }
    }

     void m_rule_not_applicable12()
    {
        ERule13Example.GenericContainerMock gcm = new ERule13Example.GenericContainerMock();
        if (gcm.size() > 0) {
            defaultMethodCall();
        }
    }

}
