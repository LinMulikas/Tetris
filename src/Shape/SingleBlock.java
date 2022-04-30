package Shape;

import GUI.Theme;
import Game.Position;

public class SingleBlock{
    public Theme theme;
    public Position position;

    SingleBlock(Position p){
        this.position = p;
    }

    SingleBlock(Theme t, Position p){
        this.theme = t;
        this.position = p;
    }
}
