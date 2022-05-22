package Shape;

import Controller.Position;
import GUI.Theme;

import static Shape.Direction.*;
import static Shape.Direction.O;

public class Shape_T extends Shape{
    public Shape_T(){
        theme = Theme.theme1;
        blocks = new SingleBlock[4];
        for(int i = 0; i < 4; i ++){
            this.blocks[i] = new SingleBlock();
        }
    }

    @Override
    public void leftRotate(){
        switch(state){
            case O:
                this.state = L;
                this.p.i += 1;
                this.p.j += 1;
                this.extendBlocks();
                return;
            case R:
                this.state = O;
                this.p.i += 1;
                this.p.j -= 1;
                this.extendBlocks();
                return;
            case D:
                this.state = R;
                this.p.i -= 1;
                this.p.j -= 1;
                this.extendBlocks();
                break;
            case L:
                this.state = D;
                this.p.i -= 1;
                this.p.j += 1;
                this.extendBlocks();
        }
    }

    @Override
    public void rightRotate(){
        switch(state){
            case O:
                this.state = R;
                this.p.i -= 1;
                this.p.j += 1;
                this.extendBlocks();
                return;
            case R:
                this.state = D;
                this.p.i += 1;
                this.p.j += 1;
                this.extendBlocks();
                return;
            case D:
                this.state = L;
                this.p.i += 1;
                this.p.j -= 1;
                this.extendBlocks();
                return;
            case L:
                this.state = O;
                this.p.i -= 1;
                this.p.j -= 1;
                this.extendBlocks();
        }

    }

    @Override
    public void extendBlocks(){
        switch(this.state){
            case O:
                this.blocks[0].position = this.p;
                this.blocks[1].position = this.p.right();
                this.blocks[2].position = this.p.right().up();
                this.blocks[3].position = this.p.right().right();
                break;
            case R:
                this.blocks[0].position = this.p;
                this.blocks[1].position = this.p.down();
                this.blocks[2].position = this.p.down().right();
                this.blocks[3].position = this.p.down().down();
                break;
            case D:
                this.blocks[0].position = this.p;
                this.blocks[1].position = this.p.left();
                this.blocks[2].position = this.p.left().down();
                this.blocks[3].position = this.p.left().left();
                break;
            case L:
                this.blocks[0].position = this.p;
                this.blocks[1].position = this.p.up();
                this.blocks[2].position = this.p.up().left();
                this.blocks[3].position = this.p.up().up();
        }
    }

    @Override
    public Shape cloneShape(){
        Shape_T result = new Shape_T();
        result.p = new Position(this.p.i, this.p.j);
        result.state = this.state;
        result.blocks = new SingleBlock[this.blocks.length];
        for(int i = 0; i < result.blocks.length; i++){
            result.blocks[i] = new SingleBlock();
        }
        return result;
    }

    @Override
    public void setTheme(Theme theme){
        this.theme = theme;
    }
}
