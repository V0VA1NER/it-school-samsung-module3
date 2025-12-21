package ru.samsung.gamestudio.objects;

import com.badlogic.gdx.physics.box2d.World;

public class BigTrash extends GameObject {

    public int health = 2;

    public BigTrash(String texturePath, int x, int y, int width, int height, short cBits, World world) {
        super(texturePath, x, y, width, height, cBits, world);
    }

    @Override
    public void hit() {
        health--;
        if (health <= 0) {
            body.getWorld().destroyBody(body);
            texture.dispose();
        }
    }
}