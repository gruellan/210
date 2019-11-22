import java.io.*;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.jsfml.graphics.*;
import org.jsfml.system.*;

import javax.swing.JOptionPane;

public class Player implements Drawable {

    private Texture playerTexture;
    private Sprite playerSprite;
    public Vector2f position;

    private boolean isOnGround = true;

    public Player(String Texture) throws IOException {
        playerTexture = new Texture();
        playerSprite = new Sprite();
        playerTexture.loadFromFile(Paths.get(Texture));
        playerSprite.setTexture(playerTexture);
        playerSprite.setOrigin(60.f, 65.f);
        position = new Vector2f(384.f, 590.f);
        playerSprite.setPosition(position);
    }

    public void changeRot(float f) {
        playerSprite.setRotation(playerSprite.getRotation() + f);
    }

    public void setRot(float f) {
        playerSprite.setRotation(f);
    }

    public void setPos(float x, float y) {
        position = new Vector2f(x, y);
        playerSprite.setPosition(position);
    }

    public void changePos(float deltaX, float deltaY) {
        position = new Vector2f(position.x + deltaX, position.y + deltaY);
        playerSprite.setPosition(position);
    }

    public void jump() {
        float startHeight = position.y;
        isOnGround = false;
        position = new Vector2f(position.x, position.y - 70);
        playerSprite.setPosition(position);
    }

    public boolean isTouching(Sprite sprite) {
        return playerSprite.getGlobalBounds().intersection(sprite.getGlobalBounds()) != null;
    }

    public void setSpriteColor(Color c) {
        playerSprite.setColor(c);
    }

    public void die() {
        JOptionPane.showMessageDialog(null, "YOU DIED",
                "YOU DIED", JOptionPane.OK_OPTION);
        this.setPos(384.f, 500.f);
    }

    @Override
    public void draw(RenderTarget arg0, RenderStates arg1) {
        if (isOnGround == false) {
            position = new Vector2f(position.x, position.y + 10);
            playerSprite.setPosition(position);
            if (position.y == 500 || position.y == 590) {
                if (position.y > 500 && position.y < 590)
                    isOnGround = false;
                isOnGround = true;
            }
        }

        arg0.draw(playerSprite);

    }
}
