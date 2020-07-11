package com.wgsoft.game.paperrogue.objects.game;

public class Item {
    public enum Type{
        ONE_HAND_WEAPON,
        TWO_HAND_WEAPON,
        HELMET,
        CHESTPLATE,
        MODIFY,
        OTHER,
        USEABLE,
    }
    public Type type;
}
