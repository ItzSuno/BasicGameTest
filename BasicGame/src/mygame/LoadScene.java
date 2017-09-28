
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/*
 * Load a .j3o scene and any required models
 * @author coder
 */

public class LoadScene {
    
    AssetManager assetManager;
    LoadScene(AssetManager assMgr){
        assetManager = assMgr;  
    }
     
    public Spatial loadOurScene(){
        
        Spatial sceneModel = assetManager.loadModel("Scenes/Scene1.j3o");
        //sceneModel.setLocalScale(.25f);
        return sceneModel;
        
    }
    
    
}
