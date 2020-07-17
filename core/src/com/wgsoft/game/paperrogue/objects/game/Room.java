package com.wgsoft.game.paperrogue.objects.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import static com.wgsoft.game.paperrogue.MyGdxGame.game;

public class Room extends Actor implements Comparable<Room> {
    public static final float SIZE = 128f;
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
        setSize(SIZE, SIZE);
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return pressed = pointer == 0;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                pressed = x > 0f && x < getWidth() && y > 0f && y < getHeight();
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(pressed){
                    pressed = false;
                    type = Type.NONE;
                    setVisible(true);
                }
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = batch.getColor();
        float r = color.r, g = color.g, b = color.b, a = color.a;
        batch.setColor(r, g, b, color.a*parentAlpha);
        batch.draw(game.skin.getRegion(pressed?"button/room/down":"button/room/up"), getX(), getY(), getWidth(), getHeight());
        switch (type){
            case MEGA_BOSS:
                batch.draw(game.skin.getRegion("button/room/icon/mega-boss"), getX(), getY(), getWidth(), getHeight());
                break;
            case BOSS:
                batch.draw(game.skin.getRegion("button/room/icon/boss"), getX(), getY(), getWidth(), getHeight());
                break;
            case JACKPOT:
                batch.draw(game.skin.getRegion("button/room/icon/jackpot"), getX(), getY(), getWidth(), getHeight());
                break;
            case PAPER_COIN:
                batch.draw(game.skin.getRegion("button/room/icon/paper-coin"), getX(), getY(), getWidth(), getHeight());
                break;
            case CHEST:
                batch.draw(game.skin.getRegion("button/room/icon/chest"), getX(), getY(), getWidth(), getHeight());
                break;
            case LUCKY_ROOM:
                batch.draw(game.skin.getRegion("button/room/icon/lucky-room"), getX(), getY(), getWidth(), getHeight());
                break;
            case REGULAR:
                batch.draw(game.skin.getRegion("button/room/icon/regular"), getX(), getY(), getWidth(), getHeight());
                break;
            case NONE:
                batch.draw(game.skin.getRegion("button/room/icon/none"), getX(), getY(), getWidth(), getHeight());
                break;
        }
        batch.setColor(r, g, b, a);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition((-game.playGameScreen.x1+x)*SIZE, (-game.playGameScreen.y1+y)*SIZE);
    }

    @Override
    public int compareTo(Room room) {
        return room.distance-distance;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(type == Type.NONE){
            if(direction != Direction.LEFT && left != null){
                left.setVisible(true);
            }
            if(direction != Direction.RIGHT && right != null){
                right.setVisible(true);
            }
            if(direction != Direction.TOP && top != null){
                top.setVisible(true);
            }
            if(direction != Direction.BOTTOM && bottom != null){
                bottom.setVisible(true);
            }
        }
    }

    public static Room at(int x, int y){
        Array.ArrayIterator<Room> iterator = new Array.ArrayIterator<>(game.playGameScreen.rooms);
        for(Room room : iterator){
            if(room.x == x && room.y == y){
                return room;
            }
        }
        return null;
    }
}
