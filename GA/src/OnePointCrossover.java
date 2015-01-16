import java.util.*;
/**
 * 一点交叉
 * @author info
 *
 */

public class OnePointCrossover implements Crossover{
	
	/*
	 * ある交叉させ、その子供を配列に追加する。
	 */
	public void clossover(Gene a,Gene b,ArrayList<Gene> genes) {
		// TODO 自動生成されたメソッド・スタブ
		Random rnd=new Random();
		
		Gene geneA=(Gene)a.clone();
		Gene geneB=(Gene)b.clone();
		
		int rndint=rnd.nextInt(geneA.getSizeOfInfo());
		
		geneA.changeGene(rndint,geneA.getSizeOfInfo(),geneA);
		geneB.changeGene(0,rndint, geneA);
		
		genes.add(geneA);
		genes.add(geneB);
	}
	/*
	public Gene crossoverGene(Gene a,Gene b){
		Random rnd=new Random();
		
		Gene geneA=(Gene)a.clone();
		Gene geneB=(Gene)b.clone();
		
		int rndint=rnd.nextInt(geneA.getSizeOfInfo());
		System.out.println("変更するポイント:"+rndint);
		
		int rndint2=rnd.nextInt(2);
		geneA.changeGene(rndint,geneA.getSizeOfInfo(),b);
		return geneA;
		//geneB.changeGene(0,rndint, geneA);
	}
	*/

}
