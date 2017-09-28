
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/*
 * Load a .j3o scene and any required models
 * @author Harry-3d
 */

public class LoadScene {
    
    AssetManager assetManager;
    LoadScene(AssetManager assMgr){
        assetManager = assMgr;  
    }
     
    public Spatial loadOurScene(Node rootNode){
        
        Spatial sceneModel = assetManager.loadModel("Scenes/Scene1.j3o");
        //sceneModel.setLocalScale(.25f);
         rootNode.attachChild(sceneModel);
         RigidBodyControl floor = new RigidBodyControl(0f);
         sceneModel.addControl(floor);
        return sceneModel;
        
    }
    
    public RigidBodyControl makeBox(Node rootNode){
         Box box = new Box(1f, 1f, 1f);
         Geometry boxGeo = new Geometry("box", box);
         Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
         mat.setColor("Color", ColorRGBA.randomColor());
         boxGeo.setMaterial(mat);
         rootNode.attachChild(boxGeo);
        boxGeo.setLocalTranslation(1f, 20f, 10f);
        RigidBodyControl boxRigid = new RigidBodyControl(2f);
        boxGeo.addControl(boxRigid);
        return boxRigid;
    }
    
    
}
