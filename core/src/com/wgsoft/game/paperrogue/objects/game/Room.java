package com.wgsoft.game.paperrogue.objects.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Room extends Actor implements Comparable<Room> {
    public enum Direction{
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        NONE
    }
    public enum Type{
        MEGA_BOSS,
        BOSS,
        JACKPOT,
        PAPER_COIN,
        CHEST,
        LUCKY_ROOM,
        REGULAR,
        NONE
    }
    public Room left, right, top, bottom;
    private Direction direction;
    public int distance;
    public int x, y;
    public Type type;
    public boolean pressed;

    public Room(Direction direction, Room from){
        this.direction = direction;
        type = Type.NONE;
        if(direction != Direction.NONE){
            x = from.x;
            y = from.y;
            distance = from.distance+1;
        }
        switch (direction){
            case LEFT:
                left = from;
                x++;
                break;
            case RIGHT:
                x--;
                right = from;
                break;
            case TOP:
                y--;
                top = from;
                break;
            case BOTTOM:
                y++;
                bottom = from;
                break;
        }
    }

    @Override
    public int compareTo(Room room) {
        return room.distance-distance;
    }
}
