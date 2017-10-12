public class LOIExample{
    int field = 10;
    int[][] fArray = {{1,2},{3,4,5}};
    Aux a = new Aux();

    public void f001(int x, int y){
        if(x == y){
        }
    }

    public void f002(int x){
        if(x >= 100){
        }
    }

    public void f003(int x){
        if(x == get()){
        }
    }

    public void f004(int x, int y){
        if(x <= y){
        }
    }

    public void f005(int x){
        if(x != field){
        }
    }

    public void f006(int x){
        if(x == a.aux001()){
        }
    }

    public void f007(int x){
        if(a.aux001() == x){
        }
    }

    public void f008(int x){
        if(x != this.field){
        }
    }

    public void f009(int x){
        if(fArray[0].length == x){
        }
    }

    private int get(){
        return 100;
    }
}

class Aux{
    public int aux001(){
        return 100;
    }

}
