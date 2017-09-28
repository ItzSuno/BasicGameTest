package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private BulletAppState bulletAppState;
    OurPlayer player = new OurPlayer();
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(200);
        
        // Load .j3o Scene from projec assets folder
        LoadScene map = new LoadScene(assetManager);
        Spatial sceneModel = map.loadOurScene();
        
        // Set up physics
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        // Make the scene solid
        rootNode.attachChild(sceneModel);
        bulletAppState.getPhysicsSpace().addAll(sceneModel);
        
        // Add player to the physicsSpace         //init player()
        bulletAppState.getPhysicsSpace().add(player.initPlayer(viewPort, flyCam));
        player.setUpKeys(inputManager);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        player.simpleUpdate(tpf, cam, listener);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
