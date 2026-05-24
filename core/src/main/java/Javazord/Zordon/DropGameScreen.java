package Javazord.Zordon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class DropGameScreen implements Screen {
    final AppEntry appEntry;

    //Lógica e input
    float gotaTempo;
    Vector2 touchPos;
    int gotasColetadas;

    //Física
    Rectangle baldeRet;
    Rectangle gotaRet;

    //Assets
    Texture fundoTx;
    Texture baldeTx;
    Sprite baldeSprite;
    Texture gotaTx;
    Array<Sprite> gotaSprites;

    Sound gotaSom;
    Music musica;


    public DropGameScreen(final AppEntry appEntry){
        this.appEntry = appEntry;

        touchPos = new Vector2();

        fundoTx = new Texture("DropGame/background.png");
        baldeTx = new Texture("DropGame/bucket.png");
        baldeSprite = new Sprite(baldeTx);
        baldeSprite.setSize(1, 1);
        gotaTx = new Texture("DropGame/drop.png");
        gotaSprites = new Array<>();

        baldeRet = new Rectangle();
        gotaRet = new Rectangle();

        gotaSom = Gdx.audio.newSound(Gdx.files.internal("DropGame/drop.mp3"));
        musica = Gdx.audio.newMusic(Gdx.files.internal("DropGame/music.mp3"));
        musica.setLooping(true);
        musica.setVolume(.5f);
    }

    public void show(){
        musica.play();
    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }

    private void input(){
        float speed = 4f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            baldeSprite.translateX(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            baldeSprite.translateX(-speed * delta);
        }

        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            appEntry.viewport.unproject(touchPos);
            baldeSprite.setCenterX(touchPos.x);
        }
    }

    private void logic() {
        float cenaLarg = appEntry.viewport.getWorldWidth();
        //float cenaAlt = appEntry.viewport.getWorldHeight();
        float baldeLarg = baldeSprite.getWidth();
        float baldeAlt = baldeSprite.getHeight();
        float delta = Gdx.graphics.getDeltaTime();

        baldeSprite.setX(MathUtils.clamp(baldeSprite.getX(), 0, cenaLarg - baldeLarg));
        baldeRet.set(baldeSprite.getX(), baldeSprite.getY(), baldeLarg, baldeAlt);

        for (int i = gotaSprites.size - 1; i >= 0; i--){
            Sprite gotaSprite = gotaSprites.get(i);
            float gotaLarg = gotaSprite.getWidth();
            float gotaAlt = gotaSprite.getHeight();

            gotaSprite.translateY(-2f * delta);
            gotaRet.set(gotaSprite.getX(), gotaSprite.getY(), gotaLarg, gotaAlt);

            if (gotaSprite.getY() < - gotaAlt) gotaSprites.removeIndex(i);
            else if (baldeRet.overlaps(gotaRet)){
                gotasColetadas++;
                gotaSprites.removeIndex(i);
                gotaSom.play();
            }
        }
        gotaTempo += delta;
        if (gotaTempo > 1f){
            gotaTempo = 0;
            newGota();
        }
    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        appEntry.viewport.apply();
        appEntry.batch.setProjectionMatrix(appEntry.viewport.getCamera().combined);
        appEntry.batch.begin();

        float cenaLarg = appEntry.viewport.getWorldWidth();
        float cenaAlt = appEntry.viewport.getWorldHeight();

        appEntry.batch.draw(fundoTx, 0, 0, cenaLarg, cenaAlt);
        baldeSprite.draw(appEntry.batch);

        appEntry.font.draw(appEntry.batch, "Gotas coletadas: " + gotasColetadas, 0, cenaAlt);

        for (Sprite gotaSprite : gotaSprites){
            gotaSprite.draw(appEntry.batch);
        }
        appEntry.batch.end();
    }

    private void newGota(){
        float gotaLarg = 1;
        float gotaAlt = 1;
        float cenaLarg = appEntry.viewport.getWorldWidth();
        float cenaAlt = appEntry.viewport.getWorldHeight();

        Sprite gotaSprite = new Sprite(gotaTx);
        gotaSprite.setSize(gotaLarg, gotaAlt);
        gotaSprite.setX(MathUtils.random(0f, cenaLarg -gotaLarg));
        gotaSprite.setY(cenaAlt);
        gotaSprites.add(gotaSprite);
    }

    public void resize(int width, int height) {
        appEntry.viewport.update(width, height, true);
    }

    @Override
    public void hide(){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void dispose() {
        fundoTx.dispose();
        baldeTx.dispose();
        gotaTx.dispose();
        gotaSom.dispose();
        musica.dispose();
    }
}
