package ru.samsung.gamestudio.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import ru.samsung.gamestudio.GameSettings;
import ru.samsung.gamestudio.GameResources;

import java.util.Random;

public class TrashObject extends GameObject {

    private static final int paddingHorizontal = 30;
    private static final Random random = new Random();

    public int livesLeft;
    public final boolean isBig; // ← добавили

    private TrashObject(int width, int height, String texturePath, int lives, World world) {
        super(
                texturePath,
                width / 2 + paddingHorizontal + random.nextInt(Math.max(1, GameSettings.SCREEN_WIDTH - 2 * paddingHorizontal - width)),
                GameSettings.SCREEN_HEIGHT + height / 2,
                width, height,
                GameSettings.TRASH_BIT,
                world
        );

        body.setLinearVelocity(new Vector2(0, -GameSettings.TRASH_VELOCITY));
        this.livesLeft = lives;
        this.isBig = (lives == 2); // ← запомнили: большой или нет
    }

    public static TrashObject createRandom(World world) {
        if (random.nextInt(3) == 0) {
            return new TrashObject(
                    GameSettings.TRASH_WIDTH,
                    GameSettings.TRASH_HEIGHT,
                    GameResources.BIG_TRASH_IMG_PATH,
                    2,
                    world
            );
        } else {
            return new TrashObject(
                    GameSettings.TRASH_WIDTH,
                    GameSettings.TRASH_HEIGHT,
                    GameResources.TRASH_IMG_PATH,
                    1,
                    world
            );
        }
    }

    public boolean isAlive() {
        return livesLeft > 0;
    }

    public boolean isInFrame() {
        return getY() + height / 2 > 0;
    }

    @Override
    public void hit() {
        livesLeft -= 1;
    }
}