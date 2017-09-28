package mygame;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Harry-3d
 */
public class WalkingOto implements AnimEventListener{
    
    AssetManager assetManager;
    private AnimChannel channel;
    private AnimControl control;
    
    public float posX;
    public float posY;
    protected float posZ;
    
    WalkingOto(AssetManager assMgr){
        assetManager = assMgr;
    }
    
    public Spatial importOto() {
        Spatial oto;
        oto = assetManager.loadModel("Models/Oto/Oto.mesh.xml");
        oto.setLocalScale(.5f);
        oto.setLocalTranslation(1f, 10f, 1f);
        
        control = oto.getControl(AnimControl.class);
        control.addListener(this);
        channel = control.createChannel();
        channel.setAnim("Walk");
        
        return oto; 
    }
    
    public Spatial makeOtoSolid(){
        Spatial spa = importOto();
        CollisionShape colShape = CollisionShapeFactory.createMeshShape(spa);
        RigidBodyControl rbc = new RigidBodyControl(colShape, 2f);
        spa.addControl(rbc);
        //node.attachChild(spa);
              
        return spa;
    }
    
    // Listener used for triggering something between two animations
    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName){
        // Unused
    }
    
    // Listener use for triggering something after an animation is done
    // Like seting a model back to its default animation.
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName){
        // Unused
    }
   
   
}
