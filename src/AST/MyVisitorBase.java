package AST;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import AST.JavaParser.ClassDeclarationContext;
import AST.JavaParser.InterfaceDeclarationContext;
import AST.DesignPatternDetection;

/*MyVisitorBase class i sadece class ve interface isim ve type lari listeye atmak icin kullanilir. 
 * Ilk once calistirilir.
 * Calisma Sirasi : 1. MyVisitorBase
 * 					2. MyVisitor
 * 					3. MyVisitor2*/
public class MyVisitorBase extends JavaBaseVisitor<Void> {

	   private String  MainClassOrInterface;
	   private String  isAbstract;
	   private String  classType;   
	   AST.DesignPatternDetection d = new DesignPatternDetection();
	   
	   public void addVertice(Integer id,String name,String type)
	   {   
	   		d.vertices.add(new Vertices(id,name,type));     	
	   }
	   
	    //Tag the Abstract Class as [A] in the tree visit because there is no child for it in ClassDeclaration. 
	    @Override
	    public Void visit(ParseTree tree) {
	    	// TODO Auto-generated method stub
	    	if (tree.getText().contains("abstractclass"))
	    	{
	    		isAbstract = "A";
	    	}
	    	//else if (tree.getText().contains("abstractinterface")) { isAbstract = "A"; }
	    	else
	    	{
	    		isAbstract = null;
	    	}

	    	return super.visit(tree);
	    }    
	    
	    @Override
	    public Void visitClassDeclaration(ClassDeclarationContext ctx) {    	
	    	
	    	classType="";

	    	//Tag the Abstract Class as [A]. ("public abstract", "abstract" e.g.) 
	    	//The others are [C], means class. 
	    	//Interfaces are caught in different visitor class. (visitInterfaceDeclaration)    	
	    	if (isAbstract != null)
	    	{
	    		MainClassOrInterface = ctx.Identifier().toString();
	    		classType = "Abstract";
	    	}
	    	else if (isAbstract == null)
	    	{ 
	    		MainClassOrInterface = ctx.Identifier().toString();
	    		classType = "Class";
	    	}
	    	if (MainClassOrInterface != null)
	    		addVertice(0,MainClassOrInterface,classType);	    		    	
	    	
	    	return super.visitClassDeclaration(ctx);
	    }
	    
	    @Override
	    public Void visitInterfaceDeclaration(InterfaceDeclarationContext ctx) {      	
	    	   	
	    	MainClassOrInterface = ctx.Identifier().toString();
	    	classType = "Interface";
	    		
	    	if (MainClassOrInterface != null)
	    		addVertice(0,MainClassOrInterface,classType);	    			    	
	    	
	    	return super.visitInterfaceDeclaration(ctx);
	    }      

	    
}
