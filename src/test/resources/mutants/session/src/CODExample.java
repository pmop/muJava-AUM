public class CODExample{

    public void f001(int x){
        if(!(x == 10)){
            x = x + 1;
        }
    }

    public void f002(Integer x){
        if(!(x instanceof Integer)){
            x = x + 1;
        }
    }
    
    public void f003(int x){
        if(!(isUtil(true))){
            x = x + 1;
        }
    }

    public void f004(int x){
        boolean b = !isUtil(true);
    }

    public void f005(int x){
        while(!(x != 0 && isUtil(true))){
            x = x + 1;
        }
    }
    
    public void f006(boolean x){
        while(!x){
            x = true;
        }
    }

    private boolean isUtil(boolean b){
        return b;
    }
}
