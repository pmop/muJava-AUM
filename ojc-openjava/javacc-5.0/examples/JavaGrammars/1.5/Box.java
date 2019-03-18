import java.util.Arrays;
import java.util.List;

   public class Box<T> {

       private T t;          

       public void add(T t) {
           this.t = t;
       }

       public T get() {
           return t;
       }

       public <U extends Number> void inspect(U u){
           System.out.println("T: " + t.getClass().getName());
           System.out.println("U: " + u.getClass().getName());
       }
      
      public static double sumOfList(List<? extends Number> list) {
           double s = 0.0;
           for (int i = 0; i < list.size();i++)
               s += list.get(i).doubleValue();
           return s;
       }

       public static void addNumbers(List<? super Integer> list) {
          for (int i = 1; i <= 10; i++) {
              list.add(i);
          }
      }
      
       
       public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
            int count = 0;
          for (T e : anArray)
              if (e.compareTo(elem) > 0)
                  ++count;
              return count;
      }

       public static void printList(List<?> list) {
           for (Object elem: list)
               System.out.print(elem + " ");
           System.out.println();
       }

       public static void main(String[] args) {
           Box<Integer> integerBox = new Box<Integer>();
           integerBox.add(new Integer(10));
           integerBox.inspect(new Integer(10));

           List<Integer> li = Arrays.asList(1, 2, 3);
           List<String>  ls = Arrays.asList("one", "two", "three");
           printList(li);
           printList(ls);
       }
}
