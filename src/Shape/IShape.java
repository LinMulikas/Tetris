package Shape;

import GUI.Theme;
import Game.Game;
import Game.Position;

import java.awt.*;

public class IShape extends Shape{
    Theme theme = new Theme(Color.cyan);
    State state = State.O;
    Position p = new Position(1,3);
    Position nextP = p.down();

    IShape(){
        blocks = new SingleBlock[4];
        for(int i = 0; i < 4; i++){
            this.blocks[i] = new SingleBlock(new Position(i + 1, i + 3));
        }
    }

    @Override
    SingleBlock[] getBlocks(){
        SingleBlock[] blocks = new SingleBlock[4];
        blocks[0].position = this.p.clone();
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
        return blocks;
    }

    @Override
    public void leftRotate(){
        switch(state){
            case O:
                this.state = State.L;
                return;
            case L:
                this.state = State.D;
                return;
            case R:
                this.state = State.O;
                return;
            case D:
                this.state = State.R;
        }
    }

    @Override
    public void rightRotate(){
        switch(state){
            case O:
                this.state = State.R;
                return;
            case L:
                this.state = State.O;
                return;
            case R:
                this.state = State.D;
                return;
            case D:
                this.state = State.L;
        }
    }



}
