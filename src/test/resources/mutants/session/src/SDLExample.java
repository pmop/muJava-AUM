public class SDLExample{

    public void f001(int x){
        if(x == 10){
            x = x + 1;
        }
    }
    
    public void f002(int x){
        if(x == 10){
            x = x + 1;
        } else {
            x = x - 1;
        }
    }
    
    public void f003(Integer x){
        if(x instanceof Integer){
            x = x + 1;
        }
    }

    public void f004(int x){
        if(isUtil(true)){
            x = x + 1;
        }
    }

    public void f005(int x){
        while(x < 100){
            x++;
        }
    }

    public void f006(int x){
        for(int i = 0;i < 100;i++){
            x++;
        }
    }

    private boolean isUtil(boolean b){
        return b;
    }
}
