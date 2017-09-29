package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

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
    int idNum;
    Node boxes = new Node();
    
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
        
        rootNode.attachChild(boxes);

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
        //bulletAppState.setDebugEnabled(true); //DEBUG VIEW
        rootNode.attachChild(otoBody);  
    }

    @Override
    public void simpleUpdate(float tpf) {
        player.simpleUpdate(tpf, cam, listener);
        
        LoadScene map = new LoadScene(assetManager);
        count += tpf;
        
        if (count > 1){
            idNum++;
            bulletAppState.getPhysicsSpace().add(map.makeBox(boxes, "id", idNum));
            count = 0;
            System.out.println("Generated Box: " + map.boxGeo.getUserData("id"));
            if (idNum -25 > 1 ){
                boxes.detachChildAt(0); // Comment out this line for unlimited boxes
            }
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
