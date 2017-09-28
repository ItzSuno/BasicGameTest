package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author Suno
 * @auther Harry-3d
 */
public class Main extends SimpleApplication {

    private BulletAppState bulletAppState;
    OurPlayer player = new OurPlayer();
    float count;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(200);
        
        // Activate physics
        bulletAppState = new BulletAppState();
        // Attach it to the SimpleApplication’s AppState manager
        stateManager.attach(bulletAppState);
        
        // Load .j3o Scene from project assets folder and add to physics space
        //LoadScene map = new LoadScene(assetManager);
        LoadScene map = new LoadScene(assetManager);
        bulletAppState.getPhysicsSpace().add(map.loadOurScene(rootNode));
        
        // Make box
        bulletAppState.getPhysicsSpace().add(map.makeBox(rootNode));

        // Add player to the physicsSpace         //init player()
        bulletAppState.getPhysicsSpace().add(player.initPlayer(viewPort, flyCam));
        player.setUpKeys(inputManager);
        
        // Add Oto
        WalkingOto oto = new WalkingOto(assetManager);
        /* Spatial myOto = oto.importOto();
        rootNode.attachChild(myOto);
        myOto.setLocalTranslation(1f, 4f, 0f);
        */
        Spatial otoBody = oto.makeOtoSolid();
        //Vector3f vec = new Vector3f(1f, 1000f, 1f);
        //otoBody.setPhysicsLocation(vec);
        //otoBody.setGravity(vec);
        //otoBody.applyCentralForce(vec);
        bulletAppState.getPhysicsSpace().add(otoBody);
        //bulletAppState.setDebugEnabled(true);
        rootNode.attachChild(otoBody);  
    }

    @Override
    public void simpleUpdate(float tpf) {
        player.simpleUpdate(tpf, cam, listener);
        LoadScene map = new LoadScene(assetManager);
        count += tpf;
        System.out.println(count);
        if (count > 1){
            bulletAppState.getPhysicsSpace().add(map.makeBox(rootNode));
            count = 0;
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
