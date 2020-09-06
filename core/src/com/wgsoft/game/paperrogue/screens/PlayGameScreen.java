package com.wgsoft.game.paperrogue.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.wgsoft.game.paperrogue.objects.game.Player;
import com.wgsoft.game.paperrogue.objects.game.Room;

import static com.wgsoft.game.paperrogue.MyGdxGame.game;

public class PlayGameScreen implements Screen {
    private Stage stage;

    public Array<Room> rooms;
    private Array.ArrayIterator<Room> roomIterator;
    private Group map;
    private Table backgroundContainer;
    private Table uiContainer;
    public int x1, y1, x2, y2;

    public int current;
    public Player[] players;

    public PlayGameScreen() {
        stage = new Stage(new ScreenViewport(), game.batch);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        map = new Group(){
            @Override
            public void act(float delta) {
                for(Room room : roomIterator){
                    if(room.isVisible()){
                        if(room.x < x1){
                            x1 = room.x;
                            ((Layout)getParent()).invalidate();
                        }else if(room.x > x2){
                            x2 = room.x;
                            ((Layout)getParent()).invalidate();
                        }
                        if(room.y < y1){
                            y1 = room.y;
                            ((Layout)getParent()).invalidate();
                        }else if(room.y > y2){
                            y2 = room.y;
                            ((Layout)getParent()).invalidate();
                        }
                    }
                }
                roomIterator.reset();
                setSize(Room.SIZE*(x2-x1+1), Room.SIZE*(y2-y1+1));
                super.act(delta);
            }
        };
        backgroundContainer = new Table(){{
            setFillParent(true);
            add(new ScrollPane(new Table(){{
                add(map);
            }})).grow();
        }};
        uiContainer = new Table(){{
            setFillParent(true);
            add(new Label("[#ffffff99]Current player:", game.skin, "small"){{
                setTouchable(Touchable.disabled);
            }});
            row();
            add(new Label("[#ffffff99]Level:", game.skin, "small"){{
                setTouchable(Touchable.disabled);
            }});
            row();
            add(new Label("[#ffffff99]Right Hand:", game.skin, "small"){{
                setTouchable(Touchable.disabled);
            }});
            row();
            add(new Label("[#ffffff99]Right Left:", game.skin, "small"){{
                setTouchable(Touchable.disabled);
            }});
            row();
            add(new Label("[#ffffff99]Body Armor:", game.skin, "small"){{
                setTouchable(Touchable.disabled);
            }});
            row();
            add(new Label("[#ffffff99]Head Armor:", game.skin, "small"){{
                setTouchable(Touchable.disabled);
            }});
            row();
            add(new Label("[#ffffff99]Right Left:", game.skin, "small"){{
                setTouchable(Touchable.disabled);
            }});
            row();
            add(new Label("[#ffffff99]Other:", game.skin, "small"){{
                setTouchable(Touchable.disabled);
            }});
            row();
            add(new Label("[#ffffff99]Usable:", game.skin, "small"){{
                setTouchable(Touchable.disabled);
            }});
            add().growX();
        }};
        stage.addActor(backgroundContainer);
        stage.addActor(uiContainer);
    }
    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {
        if((float)width/height > 1600f/960f){
            ((ScreenViewport)stage.getViewport()).setUnitsPerPixel(960f/height);
        }else{
            ((ScreenViewport)stage.getViewport()).setUnitsPerPixel(1600f/width);
        }
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void createGame(){
        current = 0;
        players = new Player[game.gameSettingsScreen.count];
        for(int i = 0; i < players.length; i++){
            players[i] = new Player();
        }
        createLevel();
    }

    public void createLevel(){
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;

        int megaBossCount;
        int bossCount;
        int jackpotCount;
        int paperCoinCount;
        int chestCount;
        int luckyRoomCount;
        int regularCount;

        int roomCount;
        int doorCount;
        int cancelledDoorCount;

        rooms = new Array<>();
        rooms.add(new Room(Room.Direction.NONE, null));
        doorCount = 4;
        roomCount = 0;
        roomCount += megaBossCount = 1;
        roomCount += bossCount = 1;
        roomCount += jackpotCount = 1;
        roomCount += paperCoinCount = (game.gameSettingsScreen.count == 1) ? 2 : MathUtils.random(2, 1+game.gameSettingsScreen.count);
        roomCount += chestCount = (game.gameSettingsScreen.count == 1) ? 4 : MathUtils.random(4, 3+game.gameSettingsScreen.count);
        roomCount += luckyRoomCount = MathUtils.random(1, game.gameSettingsScreen.count*2);
        roomCount += regularCount = MathUtils.random(1, 2+game.gameSettingsScreen.count*3);
        while(roomCount > 0){
            cancelledDoorCount = 0;
            Array.ArrayIterator<Room> iterator = new Array.ArrayIterator<>(rooms);
            for(Room room : iterator){
                if(Room.at(room.x-1, room.y) == null){
                    if(MathUtils.randomBoolean(1f/(doorCount-cancelledDoorCount))){
                        room.left = new Room(Room.Direction.RIGHT, room);
                        rooms.add(room.left);
                        roomCount--;
                        if(Room.at(room.left.x-1, room.left.y) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.left.x+1, room.left.y) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.left.x, room.left.y+1) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.left.x, room.left.y-1) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        break;
                    }else{
                        cancelledDoorCount++;
                    }
                }
                if(Room.at(room.x+1, room.y) == null){
                    if(MathUtils.randomBoolean(1f/(doorCount-cancelledDoorCount))){
                        room.right = new Room(Room.Direction.LEFT, room);
                        rooms.add(room.right);
                        roomCount--;
                        if(Room.at(room.right.x-1, room.right.y) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.right.x+1, room.right.y) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.right.x, room.right.y+1) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.right.x, room.right.y-1) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        break;
                    }else{
                        cancelledDoorCount++;
                    }
                }
                if(Room.at(room.x, room.y+1) == null){
                    if(MathUtils.randomBoolean(1f/(doorCount-cancelledDoorCount))){
                        room.top = new Room(Room.Direction.BOTTOM, room);
                        rooms.add(room.top);
                        roomCount--;
                        if(Room.at(room.top.x-1, room.top.y) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.top.x+1, room.top.y) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.top.x, room.top.y+1) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.top.x, room.top.y-1) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        break;
                    }else{
                        cancelledDoorCount++;
                    }
                }
                if(Room.at(room.x, room.y-1) == null){
                    if(MathUtils.randomBoolean(1f/(doorCount-cancelledDoorCount))){
                        room.bottom = new Room(Room.Direction.TOP, room);
                        rooms.add(room.bottom);
                        roomCount--;
                        if(Room.at(room.bottom.x-1, room.bottom.y) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.bottom.x+1, room.bottom.y) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.bottom.x, room.bottom.y+1) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        if(Room.at(room.bottom.x, room.bottom.y-1) == null){
                            doorCount++;
                        }else{
                            doorCount--;
                        }
                        break;
                    }else{
                        cancelledDoorCount++;
                    }
                }
            }
        }
        rooms.shuffle();
        rooms.sort();
        int i = 0;
        while(megaBossCount > 0){
            rooms.get(i).type = Room.Type.MEGA_BOSS;
            i++;
            megaBossCount--;
        }
        while(bossCount > 0){
            rooms.get(i).type = Room.Type.BOSS;
            i++;
            bossCount--;
        }
        while(jackpotCount+paperCoinCount+chestCount+luckyRoomCount+regularCount > 0){
            int rand = MathUtils.random(1, jackpotCount+paperCoinCount+chestCount+luckyRoomCount+regularCount);
            if(rand <= jackpotCount){
                rooms.get(i).type = Room.Type.JACKPOT;
                jackpotCount--;
            }else if((rand -= jackpotCount) <= paperCoinCount){
                rooms.get(i).type = Room.Type.PAPER_COIN;
                paperCoinCount--;
            }else if((rand -= paperCoinCount) <= chestCount){
                rooms.get(i).type = Room.Type.CHEST;
                chestCount--;
            }else if(rand - chestCount <= luckyRoomCount){
                rooms.get(i).type = Room.Type.LUCKY_ROOM;
                luckyRoomCount--;
            }else{
                rooms.get(i).type = Room.Type.REGULAR;
                regularCount--;
            }
            i++;
        }
        map.clear();
        Array.ArrayIterator<Room> iterator = new Array.ArrayIterator<>(rooms);
        for(Room room : iterator){
            room.setVisible(false);
            map.addActor(room);
        }
        rooms.get(rooms.size-1).setVisible(true);

        roomIterator = new Array.ArrayIterator<>(rooms);
    }
}
