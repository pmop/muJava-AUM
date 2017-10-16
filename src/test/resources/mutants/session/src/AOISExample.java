public class AOISExample{

    int field = 10;

    public void f001(){
        int x = 10;
        int y = x;
    }

    public int f002(){
        int x = 10;
        return x;
    }

    public int f003(int x){
        if(10 == 10)
            return x;
        else
            return 10;
    }

    public void f004(int x){
        int y = 1;
        while(y <= 10)
            x = x + 1;
    }

    public void f005(int x){
        x = field;
    }

    public int f006(){
        return field;
    }

    public int f007(int x){
        int y = 10;
        return x + y - x;
    }

    public void f008(int x){
        int y = 10;
        int z = 20;
        x = x * (y - z + x);
    }

    public void f009(){
        if(field > 10){
            int x = 5;
            int z = x;
        }
    }

    public void f010(){
        try{
            int x = 5;
            int z = x;
        }catch(Exception e){
            
        }
    }
}
