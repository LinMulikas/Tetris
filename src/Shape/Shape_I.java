package Shape;

import GUI.Theme;
import Controller.Position;

import static Shape.Direction.*;

public class Shape_I extends Shape{
    Position p = new Position(  1, 3);

    public Shape_I(){
        theme = Theme.theme1;
        blocks = new SingleBlock[4];
        for(int i = 0; i < 4; i ++){
            this.blocks[i] = new SingleBlock();
        }
    }

    @Override
    public void extendBlocks(){
        this.blocks[0].position = this.p;
        switch(this.state){
            case O:
                blocks[1].position = blocks[0].position.right();
                blocks[2].position = blocks[1].position.right();
                blocks[3].position = blocks[2].position.right();
                break;
            case L:
                blocks[1].position = blocks[0].position.up();
                blocks[2].position = blocks[1].position.up();
                blocks[3].position = blocks[2].position.up();
                break;
            case D:
                blocks[1].position = blocks[0].position.left();
                blocks[2].position = blocks[1].position.left();
                blocks[3].position = blocks[2].position.left();
                break;
            case R:
                blocks[1].position = blocks[0].position.down();
                blocks[2].position = blocks[1].position.down();
                blocks[3].position = blocks[2].position.down();
                break;
        }
    }

    @Override
    public SingleBlock[] getBlocks(){
        return this.blocks;
    }

    @Override
    public void leftRotate(){
        switch(state){
            case O:
                this.state = L;
                this.p.i += 2;
                this.p.j += 1;
                return;
            case R:
                this.state = O;
                this.p.i += 1;
                this.p.j -= 2;
                return;
            case D:
                this.state = R;
                this.p.i -= 2;
                this.p.j -= 1;
                break;
            case L:
                this.state = D;
                this.p.i -= 1;
                this.p.j += 2;
        }
    }

    @Override
    public void rightRotate(){
        switch(state){
            case O:
                this.state = R;
                this.p.i -= 1;
                this.p.j += 2;
                return;
            case R:
                this.state = D;
                this.p.i += 2;
                this.p.j += 1;
                return;
            case D:
                this.state = L;
                this.p.i += 1;
                this.p.j -= 2;
                return;
            case L:
                this.state = O;
                this.p.i -= 2;
                this.p.j -= 1;
        }
    }

    @Override
    public void setTheme(Theme theme){
        switch(theme){
            case theme1:
                for(SingleBlock block : blocks){
                    block.setTheme("Cyan", 1);
                }
                break;
            case theme2:
                for(SingleBlock block : blocks){
                    block.setTheme("Cyan", 2);
                }
                break;
            case theme3:
                for(SingleBlock block : blocks){
                    block.setTheme("Cyan", 3);
                }
                break;
        }
    }
}
