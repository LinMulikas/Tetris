package Controller;

public class Position implements Cloneable{
    public int i;
    public int j;

    public Position(int a, int b){
        this.i = a;
        this.j = b;
    }

    @Override
    public Position clone(){
        try{
            // TODO: 复制此处的可变状态，这样此克隆就不能更改初始克隆的内部
            return (Position) super.clone();
        }
        catch(CloneNotSupportedException e){
            throw new AssertionError();
        }
    }

    public void goDown(){
        this.i ++;
    }

    public void goUp(){
        this.i --;
    }

    public void goLeft(){
        this.j --;
    }

    public void goRight(){
        this.j ++;
    }

    public Position up(){
        return new Position(this.i - 1, this.j);
    }

    public Position down(){
        return new Position(this.i + 1, this.j);
    }

    public Position left(){
        return new Position(this.i, this.j - 1);
    }

    public Position right(){
        return new Position(this.i, this.j + 1);
    }

    public void setI(int i){
        this.i = i;
    }

    public void setJ(int j){
        this.j = j;
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }
}
