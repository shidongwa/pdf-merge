package javaei.h2p;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class XmlTree extends JTree {

	public void expandPath(TreePath path) {
		TreeModel model = getModel();
		if (path != null && model != null) {
			setExpandedState(path, true);
		}
	}

}
