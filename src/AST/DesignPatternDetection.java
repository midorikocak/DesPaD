/*
 * Created by JFormDesigner on Sat Dec 05 12:24:57 EET 2015
 */

package AST;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
//import java.io.FileNotFoundException;
import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.internal.core.util.InnerClassesAttribute;

/**
 * @author Murat Oruc
 */



public class DesignPatternDetection extends JFrame {
	
	public static List<Nodes> dugumler = new ArrayList<Nodes>();
	public static List<Nodes> methods = new ArrayList<Nodes>();
	public static List<Vertices> vertices = new ArrayList<Vertices>();
	
	public String overlap = "";
	public String includeInnerClasses = "";
	public String programPath = "";
	
	private static final long serialVersionUID = 1L;
	
	public DesignPatternDetection() {
		initComponents();
		tfProgramPath.setText("/home/murat/Masaüstü/eclipse/workspace/AST");
	}

	private void btPathFinderActionPerformed(ActionEvent e) {
		// TODO add your code here
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showDialog(null,null);
        tfPath.setText(f.getSelectedFile().toString());
        
        vertices.clear();
        dugumler.clear();
        methods.clear();
        
        //System.out.println(f.getCurrentDirectory());
        //System.out.println(f.getSelectedFile());
	}
	
	private void btProgramPathActionPerformed(ActionEvent e) {
		// TODO add your code here
		JFileChooser f2 = new JFileChooser();
		f2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f2.showDialog(null,null);
        tfProgramPath.setText(f2.getSelectedFile().toString());
        //System.out.println(f.getCurrentDirectory());
        //System.out.println(f.getSelectedFile());
	}
	
	private void btRunActionPerformed(ActionEvent e) throws FileNotFoundException, IOException, InterruptedException {
		
		if (tfProjectName.getText().equals("") || tfProjectName.getText().equals(null)){
			JOptionPane.showMessageDialog(null, "You have to enter the Project's name!");
			return;
		}
		
		overlap = "";
		programPath = "";	
		
		Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
		setCursor(hourglassCursor);
    
		//Baslangic parametreleri, mutlaka gir...
        Boolean sourceCodeGraph 		= true;
	    Boolean sourceCodeGraphDetail	= true;
		Boolean designPatternGraph 		= true;
		Boolean OnlyTerminalCommands	= true;
		String designpatternName		= cbSelectionDP.getSelectedItem().toString();
		String projectName				= tfProjectName.getText();
		Double threshold				= Double.parseDouble((tfThreshold.getText()));
		
		if (chbOverlap.isSelected() == true){
			overlap = " -overlap ";
		}
		else{
			overlap = "";
		}

		if (chbInnerClass.isSelected() == true){
			includeInnerClasses = "Yes";
		}
		else{
			includeInnerClasses = "No";
		}
		
		programPath = tfProgramPath.getText();				  	 
	   	
	    //create "project" directory
	    String directoryNameProject = programPath+"/Projects/"+projectName+"/";
	    File directoryProject = new File(String.valueOf(directoryNameProject));
	    if (! directoryProject.exists()){
	    	directoryProject.mkdir();
	    }
	    
	    //create "source" directory
	    String directoryName = programPath+"/Projects/"+projectName+"/source/";
	    File directory = new File(String.valueOf(directoryName));
	    if (! directory.exists()){
	        directory.mkdir();
	    }
	    else{
	    	FileUtils.deleteDirectory(new File(directoryName));
	    	directory.mkdir();
	    }
	    
	    //create "inputs" directory
	    String directoryName2 = programPath+"/Projects/"+projectName+"/inputs/";
	    File directory2 = new File(String.valueOf(directoryName2));
	    if (! directory2.exists()){
	        directory2.mkdir();
	    }

	    
	    //create "outputs" directory
	    String directoryName3 = programPath+"/Projects/"+projectName+"/outputs/";	    
	    File directory3 = new File(String.valueOf(directoryName3));
	    if (! directory3.exists()){
	        directory3.mkdir();
	    }
	    
	    
	    //create "batch" directory
	    String directoryName4 = programPath+"/Projects/"+projectName+"/batch/";
	    File directory4 = new File(String.valueOf(directoryName4));
	    if (! directory4.exists()){
	        directory4.mkdir();
	    }
	    else{
	    	FileUtils.deleteDirectory(new File(directoryName4));
	    	directory4.mkdir();
	    }
	    
		//create "designpatternName+inputs" directory
	    String directoryName5 = programPath+"/Projects/"+projectName+"/inputs/"+designpatternName+"_inputs/";
	    File directory5 = new File(String.valueOf(directoryName5));
	    if (! directory5.exists()){
	        directory5.mkdir();
	    }
	    else{
	    	FileUtils.deleteDirectory(new File(directoryName5));
	    	directory5.mkdir();
	    }
	    
	    //create "designpatternName+outputs" directory
	    String directoryName6 = programPath+"/Projects/"+projectName+"/outputs/"+designpatternName+"_outputs/";
	    File directory6 = new File(String.valueOf(directoryName6));
	    if (! directory6.exists()){
	        directory6.mkdir();
	    }
	    else{
	    	FileUtils.deleteDirectory(new File(directoryName6));
	    	directory6.mkdir();
	    }
	    
	    File dir = new File(tfPath.getText());
		FileWalker fw = new FileWalker();
		List<String> directoryListing = new ArrayList<String>();
		directoryListing.clear();
		
		directoryListing = fw.displayDirectoryContents(dir);
        
	   // File[] directoryListing = dir.listFiles();	   	  
        
		   
	   	//1. visit 
	   	if (directoryListing != null) 
	   	{
	   	    for (String child : directoryListing) 
	   	    {
	   	    	
	   	    	if (child.toString().contains(".java") && !child.toString().contains("package-info"))
	   	    	{
	   	    		System.out.println(child);
	   	    		
		    	    	ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(child));
		
		    	        // we'll parse this file
		    	        JavaLexer lexer = new JavaLexer(input);
		    	        CommonTokenStream tokens = new CommonTokenStream(lexer);
		    	        JavaParser parser = new JavaParser(tokens);
		    	        ParseTree tree = parser.compilationUnit(); // see the grammar 
		    	        
		
		    	        MyVisitorBase visitorbase = new MyVisitorBase(); // extends JavaBaseVisitor<Void>
		    	                                                // and overrides the methods
		    	                                                // you're interested
		    	        visitorbase.visit(tree); 	        
		    	        
	   	    	}
	   	    	
	   	    }
	   	} 
	   	else 
	   	{
	   		JOptionPane.showMessageDialog(null, "Could not find the path...");
	   		return;
	   	}
	   	
	   	//2. visit 
	   	if (directoryListing != null) 
	   	{
	   	    for (String child : directoryListing) 
	   	    {
	   	    	
	   	    	if (child.toString().contains(".java") && !child.toString().contains("package-info"))
	   	    	{
	   	    		//System.out.println(child);
	   	    		
		    	    	ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(child));
		
		    	        // we'll parse this file
		    	        JavaLexer lexer = new JavaLexer(input);
		    	        CommonTokenStream tokens = new CommonTokenStream(lexer);
		    	        JavaParser parser = new JavaParser(tokens);
		    	        ParseTree tree = parser.compilationUnit(); // see the grammar 
		    	        
		
		    	        MyVisitor visitor = new MyVisitor(); // extends JavaBaseVisitor<Void>
		    	                                                // and overrides the methods
		    	                                                // you're interested
		    	        visitor.includeInnerClasses = includeInnerClasses;
		    	        visitor.modifiersSet();
		    	        visitor.visit(tree); 	        
		    	        
	   	    	}
	   	    	
	   	    }
	   	}
	  
	   	//3.visit
	   	if (directoryListing != null) 
	   	{
	   	    for (String child : directoryListing) 
	   	    {
	   	    	
	   	    	if (child.toString().contains(".java") && !child.toString().contains("package-info"))
	   	    	{
	   	    		//System.out.println(child);
	   	    		
		    	    	ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(child));
		
		    	        // we'll parse this file
		    	        JavaLexer lexer = new JavaLexer(input);
		    	        CommonTokenStream tokens = new CommonTokenStream(lexer);
		    	        JavaParser parser = new JavaParser(tokens);
		    	        ParseTree tree = parser.compilationUnit(); // see the grammar 
		    	        
		    	        MyVisitor2 visitor2 = new MyVisitor2();
		    	        visitor2.modifiersSet();
		    	        visitor2.visit(tree);
		    	        
	   	    	}
	   	    	
	   	    }
	   	} 
	   	  
	    try {
			   Proba p = new Proba();	   	
			   	p.start(sourceCodeGraph,
			   			sourceCodeGraphDetail,
			   			designPatternGraph,
			   			designpatternName,
			   			OnlyTerminalCommands,
			   			projectName,
			   			threshold,
			   			overlap,
			   			programPath);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	   	
	   	
	   	taInfo.setText("----------"+designpatternName+" PATTERN ("+projectName+")-------------"+"\n");
	   	taInfo.append("1. Project's classes ASTs created."+"\n");
	   	taInfo.append("2. After treewalk of ASTs, Graph Model is created.(/Projects/"+projectName+"/source)"+"\n");
	   	taInfo.append("3. Pool of Desing Pattern Templates is created.(/Projects/"+projectName+"/inputs/"+designpatternName+"_inputs)"+"\n");
	   	taInfo.append("4. Heuristic function of shell script file is created.(/Projects/"+projectName+"/batch)"+"\n");
	   	  
	   	//p.start2();
	   	
	    Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	    setCursor(normalCursor);
	    
	    vertices.clear();
	    dugumler.clear();
	    methods.clear();
	    
	   	JOptionPane.showMessageDialog(null, "Graph Model is successfully completed!");
	}
	
	public class FileWalker{
		public List<String> pathList = new ArrayList<String>();
		
		public List<String> displayDirectoryContents(File dir) {
			try {
				File[] files = dir.listFiles();
				
				
				for (File file : files) {
					if (file.isDirectory()) {
						//System.out.println("directory:" + file.getCanonicalPath());
						displayDirectoryContents(file);
					} else {
						if(file.getCanonicalPath().endsWith(".java")){
							pathList.add(file.getCanonicalPath());
							//System.out.println("     file:" + file.getCanonicalPath());
						}
						
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return pathList;
		}
	}
	
	private void thisWindowClosing(WindowEvent e) {
		// TODO add your code here
		System.exit(0);
	}

	private void btRunSgisoActionPerformed(ActionEvent e) throws IOException, InterruptedException {
		// TODO add your code here
		try {
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			setCursor(hourglassCursor);			
			
			String myCommand = programPath+"/Projects/script_"+cbSelectionDP.getSelectedItem().toString()+".sh";
			ProcessBuilder pb = new ProcessBuilder(myCommand);
			
			long startTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());	
			
			Process p = pb.start();     // Start the process.
			p.waitFor();                // Wait for the process to finish.
			
			long endTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

			long duration = (endTime - startTime);
			String duration_ = Long.toString(duration);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			PrintWriter writer_time = new PrintWriter(programPath+"/Projects/" + tfProjectName.getText() + "/outputs/"+cbSelectionDP.getSelectedItem().toString()+"_outputs/time_" + cbSelectionDP.getSelectedItem().toString() + ".txt" );
			writer_time.println("Script lasts "+duration_+" seconds. --"+dateFormat.format(date).toString()+"--");
			writer_time.close();
			
			taInfo.append("5. Subdue-Sgiso isomorphic search algorithm runned in "+duration_+" seconds.(/Projects/"+tfProjectName.getText()+"/outputs/"+cbSelectionDP.getSelectedItem().toString()+"_outputs)"+"\n");   	 
			
		    Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		    setCursor(normalCursor);
		    
		    //remove the executable file.
			String myShellScript = "";
			myShellScript = "rm "+programPath+"/Projects/script_" + cbSelectionDP.getSelectedItem().toString() + ".sh ";
			Runtime.getRuntime().exec(myShellScript);	
			
		    JOptionPane.showMessageDialog(null, "Sgiso script executed successfully");
			
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2.toString());
			JOptionPane.showMessageDialog(null,e2.toString());
			e2.printStackTrace();
		}

	}

	private void button1ActionPerformed(ActionEvent e) throws FileNotFoundException {
		// TODO add your code here
		String Title_ = cbSelectionDP.getSelectedItem().toString()+" OUTPUTS";
		File f_ = new File(tfProgramPath.getText()+"/Projects/"+tfProjectName.getText()+"/outputs/"+cbSelectionDP.getSelectedItem().toString()+"_outputs/");

		OutputFilesFindOverlaps o = new OutputFilesFindOverlaps();
		
		try {
			o.DeleteRedundant(f_);
			o.ClearOutputs(f_);
			o.ParseOutputs(Title_,f_);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		taInfo.append("6. Overlapped outputs are excluded."+"\n");   	 	
		JOptionPane.showMessageDialog(null,"Overlap outputs are excluded!");
	}

	private void button2ActionPerformed(ActionEvent e) throws IOException {
		// TODO add your code here
		File f = new File(programPath+"/Projects/"+tfProjectName.getText()+"/outputs/"+cbSelectionDP.getSelectedItem().toString()+"_outputs/");
		
		FilenameFilter textFilter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".dot");
	        }
	    };
	    
	    String fileName="";	    
	    File[] files = f.listFiles(textFilter);
	    String myShellScript = "";		
		
	    Proba p = new Proba();
	    for (File file : files) 
	    {	    	
	    	if (file.toString().contains("final_out"))
	    	{
		    	fileName = f + "/" + file.getName();
		    	myShellScript = "dot -Tpng "+ fileName + " -o " + fileName.replace(".dot", ".png");
				Runtime.getRuntime().exec(myShellScript);
	    	}
	    	//p.graphDrawing(file.toString());
	    }
	    fileName = programPath+"/Projects/"+ tfProjectName.getText() + "/source/" + tfProjectName.getText() + ".dot";
	    myShellScript = "dot -Tpng "+ fileName + " -o " + fileName.replace(".dot", ".png");
		Runtime.getRuntime().exec(myShellScript);
	    
	    taInfo.append("7. Source and output graphs are plotted. (They exist in the \"source\" and \"outputs\" directory)"+"\n"); 
	    JOptionPane.showMessageDialog(null, "Source and output graphs are plotted.");
	}

	private void initComponents() {
		
		
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Murat Oruc
		btPathFinder = new JButton();
		label1 = new JLabel();
		tfPath = new JTextField();
		label2 = new JLabel();
		cbSelectionDP = new JComboBox<>();
		btRun = new JButton();
		label3 = new JLabel();
		tfProjectName = new JTextField();
		label4 = new JLabel();
		tfThreshold = new JTextField();
		chbOverlap = new JCheckBox();
		btRunSgiso = new JButton();
		scrollPane1 = new JScrollPane();
		taInfo = new JTextArea();
		label5 = new JLabel();
		tfProgramPath = new JTextField();
		btProgramPath = new JButton();
		button1 = new JButton();
		button2 = new JButton();
		chbInnerClass = new JCheckBox();

		//======== this ========
		setTitle("DesPaD (Design Pattern Detector)");
		setIconImage(((ImageIcon)UIManager.getIcon("FileView.computerIcon")).getImage());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				thisWindowClosing(e);
			}
		});
		Container contentPane = getContentPane();

		//---- btPathFinder ----
		btPathFinder.setText("...");
		btPathFinder.setFont(btPathFinder.getFont().deriveFont(btPathFinder.getFont().getSize() + 1f));
		btPathFinder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btPathFinderActionPerformed(e);
			}
		});

		//---- label1 ----
		label1.setText("Source Code Directory Path");

		//---- tfPath ----
		tfPath.setText("...");
		tfPath.setEditable(false);
		tfPath.setForeground(Color.blue);
		tfPath.setFont(tfPath.getFont().deriveFont(tfPath.getFont().getStyle() | Font.BOLD));

		//---- label2 ----
		label2.setText("Select Design Pattern");

		//---- cbSelectionDP ----
		cbSelectionDP.setModel(new DefaultComboBoxModel<>(new String[] {
			"FACTORY_METHOD",
			"PROTOTYPE",
			"ABSTRACT_FACTORY",
			"BUILDER",
			"SINGLETON",
			"COMPOSITE",
			"FACADE",
			"DECORATOR",
			"DECORATOR2",
			"BRIDGE",
			"FLYWEIGHT",
			"ADAPTER",
			"PROXY",
			"MEDIATOR",
			"STATE",
			"OBSERVER",
			"TEMPLATE_METHOD",
			"TEMPLATE_METHOD2",
			"COMMAND",
			"CHAIN_OF_RESPONSIBILITY",
			"INTERPRETER",
			"MEMENTO",
			"ITERATOR",
			"STRATEGY",
			"VISITOR"
		}));

		//---- btRun ----
		btRun.setText("1. Build Model Graph");
		btRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btRunActionPerformed(e);
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//---- label3 ----
		label3.setText("Project Name");

		//---- label4 ----
		label4.setText("Threshold");

		//---- tfThreshold ----
		tfThreshold.setText("0.0");

		//---- chbOverlap ----
		chbOverlap.setText("Overlap");

		//---- btRunSgiso ----
		btRunSgiso.setText("2. Run Subdue-Sgiso Algorithm");
		btRunSgiso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btRunSgisoActionPerformed(e);
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(taInfo);
		}

		//---- label5 ----
		label5.setText("Program Directory Path");

		//---- tfProgramPath ----
		tfProgramPath.setEditable(false);
		tfProgramPath.setForeground(Color.blue);
		tfProgramPath.setFont(tfProgramPath.getFont().deriveFont(tfProgramPath.getFont().getStyle() | Font.BOLD));

		//---- btProgramPath ----
		btProgramPath.setText("...");
		btProgramPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btProgramPathActionPerformed(e);
			}
		});

		//---- button1 ----
		button1.setText("3. Exclude overlap outputs");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					button1ActionPerformed(e);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//---- button2 ----
		button2.setText("4. Graph Representations");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					button2ActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//---- chbInnerClass ----
		chbInnerClass.setText("Include Inner Classes");
		chbInnerClass.setSelected(true);

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addComponent(label1)
							.addGap(21, 433, Short.MAX_VALUE))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(label4)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addComponent(button1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
										.addComponent(tfThreshold, GroupLayout.Alignment.LEADING)
										.addComponent(cbSelectionDP, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
										.addComponent(label2, GroupLayout.Alignment.LEADING)
										.addComponent(btRun, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
									.addGap(30, 30, 30)
									.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(label3)
										.addComponent(tfProjectName, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
										.addGroup(contentPaneLayout.createSequentialGroup()
											.addComponent(chbOverlap)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(chbInnerClass))
										.addComponent(btRunSgiso, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
										.addComponent(button2, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))))
							.addGap(0, 56, Short.MAX_VALUE))
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGroup(contentPaneLayout.createParallelGroup()
										.addGroup(contentPaneLayout.createSequentialGroup()
											.addComponent(label5)
											.addGap(0, 418, Short.MAX_VALUE))
										.addComponent(tfProgramPath, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
										.addComponent(tfPath, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(btPathFinder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btProgramPath, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
							.addContainerGap())))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(29, 29, 29)
					.addComponent(label1)
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(tfPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btPathFinder, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(label5)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(tfProgramPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btProgramPath))
					.addGap(18, 18, 18)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label2)
						.addComponent(label3))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(cbSelectionDP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfProjectName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addComponent(label4)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(tfThreshold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chbOverlap)
						.addComponent(chbInnerClass))
					.addGap(18, 18, 18)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(btRun, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btRunSgiso, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(button2, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
						.addComponent(button1, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
					.addGap(18, 18, 18)
					.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
					.addContainerGap())
		);
		setSize(630, 625);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Murat Oruc
	private JButton btPathFinder;
	private JLabel label1;
	private JTextField tfPath;
	private JLabel label2;
	private JComboBox<String> cbSelectionDP;
	private JButton btRun;
	private JLabel label3;
	private JTextField tfProjectName;
	private JLabel label4;
	private JTextField tfThreshold;
	private JCheckBox chbOverlap;
	private JButton btRunSgiso;
	private JScrollPane scrollPane1;
	private JTextArea taInfo;
	private JLabel label5;
	private JTextField tfProgramPath;
	private JButton btProgramPath;
	private JButton button1;
	private JButton button2;
	private JCheckBox chbInnerClass;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
