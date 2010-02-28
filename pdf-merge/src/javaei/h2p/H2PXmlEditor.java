package javaei.h2p;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javaei.H2pXmlUtil;
import javaei.pdf.XmlValidator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class H2PXmlEditor extends JFrame {

	private JPanel rootPanel = null;
	private JTabbedPane contentTabbedPane = null;
	private JPanel validatePanel = null;
	private JPanel editPanel = null;
	private JTextField h2pXmlFilePath = null;
	private JButton opencheck = null;
	private JButton validate = null;
	private JTextArea validatemsg = null;
	private JScrollPane jScrollPane = null;
	private JScrollPane treeScrollPane1 = null;
	private JTree xmlTree = null;
	private JTextField hrefname = null;
	private JTextField hrefvalue = null;
	private JLabel label1 = null;
	private JLabel jLabel = null;
	private JButton add = null;
	private JButton open = null;
	private JButton save = null;
	private JButton saveas = null;
	private JTextField currenth2p = null;
	private JLabel jLabel1 = null;
	private JButton update = null;
	private JButton delete = null;
	private JButton logoBtn = null;
	

	/**
	 * This method initializes 
	 * 
	 */
	public H2PXmlEditor() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(770, 555));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(getRootPanel());
        H2pXmlUtil.locateCenter(this);
        this.setTitle("h2p文件验证和编辑");
        this.setResizable(false);
			
	}

	/**
	 * This method initializes rootPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRootPanel() {
		if (rootPanel == null) {
			rootPanel = new JPanel();
			rootPanel.setLayout(null);
			rootPanel.add(getContentTabbedPane(), null);
		}
		return rootPanel;
	}

	/**
	 * This method initializes contentTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getContentTabbedPane() {
		if (contentTabbedPane == null) {
			contentTabbedPane = new JTabbedPane();
			contentTabbedPane.setBounds(new Rectangle(4, 4, 752, 513));
			contentTabbedPane.addTab("h2p.xml校验", null, getValidatePanel(), null);
			contentTabbedPane.addTab("h2p.xml编辑", null, getEditPanel(), null);
		}
		return contentTabbedPane;
	}

	/**
	 * This method initializes validatePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getValidatePanel() {
		if (validatePanel == null) {
			validatePanel = new JPanel();
			validatePanel.setLayout(null);
			validatePanel.add(getH2pXmlFilePath(), null);
			validatePanel.add(getOpencheck(), null);
			validatePanel.add(getValidate(), null);
			validatePanel.add(getJScrollPane(), null);
		}
		return validatePanel;
	}

	/**
	 * This method initializes editPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEditPanel() {
		if (editPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(494, 47, 93, 22));
			jLabel1.setText("当前的 h2p 文件");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(495, 185, 61, 18));
			jLabel.setText("超级链接");
			label1 = new JLabel();
			label1.setBounds(new Rectangle(495, 124, 36, 26));
			label1.setText("标题");
			editPanel = new JPanel();
			editPanel.setLayout(null);
			editPanel.add(getTreeScrollPane1(), null);
			editPanel.add(getHrefname(), null);
			editPanel.add(getHrefvalue(), null);
			editPanel.add(label1, null);
			editPanel.add(jLabel, null);
			editPanel.add(getAdd(), null);
			editPanel.add(getOpen(), null);
			editPanel.add(getSave(), null);
			editPanel.add(getSaveAs(), null);
			editPanel.add(getCurrenth2p(), null);
			editPanel.add(jLabel1, null);
			editPanel.add(getUpdate(), null);
			editPanel.add(getDelete(), null);
			editPanel.add(getLogoBtn(), null);
		}
		return editPanel;
	}

	/**
	 * This method initializes h2pXmlFilePath	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getH2pXmlFilePath() {
		if (h2pXmlFilePath == null) {
			h2pXmlFilePath = new JTextField();
			h2pXmlFilePath.setBounds(new Rectangle(105, 31, 250, 25));
		}
		return h2pXmlFilePath;
	}

	/**
	 * This method initializes opencheck	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOpencheck() {
		if (opencheck == null) {
			opencheck = new JButton();
			opencheck.setBounds(new Rectangle(365, 31, 113, 27));
			opencheck.setText("选择h2p文件");
			opencheck.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					File tobevalidated = H2pXmlUtil.getSelectedFile(H2PXmlEditor.this);
					if(tobevalidated != null){
						getH2pXmlFilePath().setText(tobevalidated.getAbsolutePath());
					}
				}
			});
		}
		return opencheck;
	}

	/**
	 * This method initializes validate	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getValidate() {
		if (validate == null) {
			validate = new JButton();
			validate.setBounds(new Rectangle(491, 31, 107, 28));
			validate.setText("验证");
			validate.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String xmlFilePath = getH2pXmlFilePath().getText();
					if(xmlFilePath != null){
						XmlValidator xmlValidator = new XmlValidator();
						try {
							xmlValidator.validateWithDTD(xmlFilePath);
							getValidatemsg().setText("验证通过!!");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							getValidatemsg().append("验证失败\n\n");
							getValidatemsg().append(e1.getMessage());
							
						}
					}
				}
			});
		}
		return validate;
	}

	/**
	 * This method initializes validatemsg	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getValidatemsg() {
		if (validatemsg == null) {
			validatemsg = new JTextArea();
		}
		return validatemsg;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(108, 64, 486, 404));
			jScrollPane.setViewportView(getValidatemsg());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes treeScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTreeScrollPane1() {
		if (treeScrollPane1 == null) {
			treeScrollPane1 = new JScrollPane();
			treeScrollPane1.setBounds(new Rectangle(12, 7, 473, 471));
			treeScrollPane1.setViewportView(getXmlTree());
		}
		return treeScrollPane1;
	}

	/**
	 * This method initializes xmlTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getXmlTree() {
		if (xmlTree == null) {
			xmlTree = new XmlTree();
			XmlNodeObject rootobj = new XmlNodeObject("我的PDF书");
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootobj);
			DefaultTreeModel model = new DefaultTreeModel(root);
			xmlTree.setModel(model);
			xmlTree.addTreeSelectionListener(new TreeSelectionListener(){
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
					XmlNodeObject xmlobj = (XmlNodeObject)selectedNode.getUserObject();
					getHrefname().setText(xmlobj.getTitle());
					getHrefvalue().setText(xmlobj.getHref());
					if(xmlobj.isRoot()){
						getDelete().setEnabled(false);
					}else{
						getDelete().setEnabled(true);
					}
				}
				
			});
			
		}
		return xmlTree;
	}
	
	private DefaultMutableTreeNode getSelectedNode(){
		TreePath selectionPath = getXmlTree().getSelectionPath();
		if(selectionPath != null){
			Object[] objs = selectionPath.getPath();
			return (DefaultMutableTreeNode)objs[objs.length - 1];
		}
		return null;
	}

	/**
	 * This method initializes hrefname	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getHrefname() {
		if (hrefname == null) {
			hrefname = new JTextField();
			hrefname.setBounds(new Rectangle(493, 154, 248, 24));
		}
		return hrefname;
	}

	/**
	 * This method initializes hrefvalue	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getHrefvalue() {
		if (hrefvalue == null) {
			hrefvalue = new JTextField();
			hrefvalue.setBounds(new Rectangle(495, 205, 246, 26));
		}
		return hrefvalue;
	}

	/**
	 * This method initializes add	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAdd() {
		if (add == null) {
			add = new JButton();
			add.setBounds(new Rectangle(495, 238, 67, 25));
			add.setText("添加");
			add.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String title = getHrefname().getText();
					String href = getHrefvalue().getText();
					if(!H2pXmlUtil.strIsNull(title)){
						XmlNodeObject xmlNodeObject = new XmlNodeObject(title,href);
						DefaultMutableTreeNode node = new DefaultMutableTreeNode(xmlNodeObject);
						DefaultMutableTreeNode selectednode = getSelectedNode();
						if(selectednode != null){
							selectednode.add(node);		
							xmlNodeObject.setParentObject((XmlNodeObject)selectednode.getUserObject());
							expandTochild(selectednode);
							getXmlTree().updateUI();
							
							getHrefname().setText("");
							getHrefvalue().setText("");
						}
					}
				}
			});
		}
		return add;
	}
	
	
	private void expandTochild(DefaultMutableTreeNode node){
		if(node.getChildCount() == 0){
			return;
		}
		DefaultMutableTreeNode lastchild = (DefaultMutableTreeNode)node.getChildAt(node.getChildCount()-1);
		TreeNode[] nodes = ((DefaultTreeModel)getXmlTree().getModel()).getPathToRoot(lastchild);
		TreePath p = new TreePath(nodes);
		getXmlTree().expandPath(p);
	}

	/**
	 * This method initializes open	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOpen() {
		if (open == null) {
			open = new JButton();
			open.setBounds(new Rectangle(502, 11, 60, 25));
			open.setText("打开");
			open.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					File opend = H2pXmlUtil.getSelectedFile(H2PXmlEditor.this);
					if(opend != null){
						String filename = opend.getAbsolutePath();
						if(!H2pXmlUtil.strIsNull(filename)){
							getCurrenth2p().setText(filename);
							XmlElementConvertor converter = new XmlElementConvertor();
							DefaultMutableTreeNode rootnode = converter.loadH2p(filename);
							((DefaultTreeModel)getXmlTree().getModel()).setRoot(rootnode);
							getXmlTree().updateUI();
						}
					}
					
				}
			});

		}
		return open;
	}

	/**
	 * This method initializes save	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSave() {
		if (save == null) {
			save = new JButton();
			save.setBounds(new Rectangle(572, 11, 60, 25));
			save.setText("保存");
			save.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)getXmlTree().getModel().getRoot();
					XmlElementConvertor convertor = new XmlElementConvertor();
					String filename= getCurrenth2p().getText();
					if(H2pXmlUtil.strIsNull(filename)){
						File savefile = H2pXmlUtil.getSaveFile(H2PXmlEditor.this);
						if(savefile != null){
							filename= savefile.getAbsolutePath();
							getCurrenth2p().setText(filename);
						}
					}
					if(!H2pXmlUtil.strIsNull(filename)){
						convertor.saveH2p(rootNode,filename);
						JOptionPane.showMessageDialog(H2PXmlEditor.this, "保存成功");
					}
					
				}
			});

		}
		return save;
	}
	
	private JButton getSaveAs() {
		if (saveas == null) {
			saveas = new JButton();
			saveas.setBounds(new Rectangle(642, 11, 75, 25));
			saveas.setText("另存为");
			saveas.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)getXmlTree().getModel().getRoot();
					XmlElementConvertor convertor = new XmlElementConvertor();
					String filename= null;
					File savefile = H2pXmlUtil.getSaveFile(H2PXmlEditor.this);
					if(savefile != null){
						filename= savefile.getAbsolutePath();
						getCurrenth2p().setText(filename);
					}
					if(!H2pXmlUtil.strIsNull(filename)){
						convertor.saveH2p(rootNode,filename);
						JOptionPane.showMessageDialog(H2PXmlEditor.this, "保存成功");
					}

				}
			});

		}
		return saveas;
	}

	/**
	 * This method initializes currenth2p	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCurrenth2p() {
		if (currenth2p == null) {
			currenth2p = new JTextField();
			currenth2p.setEnabled(false);
			currenth2p.setBounds(new Rectangle(493, 74, 251, 25));
		}
		return currenth2p;
	}

	/**
	 * This method initializes update	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUpdate() {
		if (update == null) {
			update = new JButton();
			update.setBounds(new Rectangle(580, 238, 67, 25));
			update.setText("更新");
			update.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String title = getHrefname().getText();
					
					if(!H2pXmlUtil.strIsNull(title)){
						XmlNodeObject xmlobj  = (XmlNodeObject)getSelectedNode().getUserObject();
						String href = getHrefvalue().getText();
						
						if(xmlobj.isRoot()){
							href = null;
						}
						xmlobj.setTitleAndHref(title, href);
						expandTochild(getSelectedNode());
						getXmlTree().updateUI();
						getHrefname().setText("");
						getHrefvalue().setText("");

					}
				}
			});
		}
		return update;
	}

	/**
	 * This method initializes delete	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDelete() {
		if (delete == null) {
			delete = new JButton();
			delete.setBounds(new Rectangle(660, 238, 67, 25));
			delete.setText("删除");
			delete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String title = getHrefname().getText();
					if(!H2pXmlUtil.strIsNull(title)){
						DefaultMutableTreeNode selectednode = getSelectedNode();
						DefaultMutableTreeNode parentnode = (DefaultMutableTreeNode)selectednode.getParent();
						if(parentnode!=null){
							parentnode.remove(selectednode);
							XmlNodeObject xmlobj  = (XmlNodeObject)selectednode.getUserObject();
							xmlobj.deleteFromParent();
							expandTochild(parentnode);
							getXmlTree().updateUI();
						}

					}
				}
			});

		}
		return delete;
	}

	/**
	 * This method initializes logoBtn	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLogoBtn() {
		if (logoBtn == null) {
			logoBtn = new JButton();
			logoBtn.setBounds(new Rectangle(496, 299, 235, 138));
			ImageIcon imageIcon = new ImageIcon(H2pXmlUtil.getClasspathFilePath("javaei/pdf/img/logo.jpg"));
			Image img = imageIcon.getImage();
			Image newimg = img.getScaledInstance(logoBtn.getWidth(), logoBtn.getHeight(), Image.SCALE_DEFAULT);
			imageIcon.setImage(newimg);
			logoBtn.setIcon(imageIcon);
			
		}
		return logoBtn;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new H2PXmlEditor().setVisible(true);
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
