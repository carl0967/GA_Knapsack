import java.util.*;
/**
 * 
 * 単純GAの管理部分
 * @author info
 *
 */

public class Management {
	private  int MAX_INFO; //遺伝子の情報データの数 
	private static int MAX_GENERATION=200;//何世代まで繰り返すか
	private static int MAX_GEENS=100; //個体総数
	
	private ArrayList<Gene> cuurentGenes=new ArrayList<Gene>(); //現世代
	private ArrayList<Gene> nextGenes=new ArrayList<Gene>(); //次世代
	private Crossover crossover;//交叉
	private KnapsackProblem knapsack;//ナップサック問題

	
	public Management(KnapsackProblem knap){
		knapsack=knap;
		this.MAX_INFO=knap.getMaxElement();
		crossover=new OnePointCrossover();//交叉は一点交叉に指定
		setUp();
	}
	public void start(){

		for(int i=0;i<MAX_GENERATION;i++){
			System.out.println(i+"回目");
			evaluationWithPrint(cuurentGenes); 
			for(int j=0;j<MAX_GEENS;j+=2){
				int a,b;
				a=roulette();
				do{
					b=roulette();
				}while(a==b);
				crossover(a,b); 
			}
			mutation(0.05);
			System.out.println();
			goNextGeneration();
		}
		
	}

	/**
	 * 次の世代交代のための処理
	 */
	public void goNextGeneration(){
		//エリート保存戦略(今の世代のエリートを次の世代の落ちこぼれと入れ替える)
		int eliteNum=searchElite(cuurentGenes);
		evaluation(nextGenes);
		int badNum=searchBad(nextGenes);
		
		nextGenes.set(badNum,  (Gene) cuurentGenes.get(eliteNum).clone());
		
		//現世代←次世代
		cuurentGenes.clear();
		
		for(Gene tmp:nextGenes){
			cuurentGenes.add(tmp);
		}

		nextGenes.clear();
	}
	/**
	 * 初期化処理
	 */
	public void setUp(){
		for(int i=0;i<MAX_GEENS;i++){
			Gene gene=new Gene(MAX_INFO);
			cuurentGenes.add(gene);
		}
	}
	/**
	 * 評価する
	 * @param genes
	 */
	public void evaluation(ArrayList<Gene> genes){
		for(int i=0;i<genes.size();i++){
			int ans=knapsack.calcMaxWeight(genes.get(i).getInfo());
			genes.get(i).setFitness(ans);
		}
	}
	/**
	 * 評価しつつその内容を表示
	 * @param genes
	 */
	public void evaluationWithPrint(ArrayList<Gene> genes){
		
		for(int i=0;i<genes.size();i++){
			int ans=knapsack.calcMaxWeight(genes.get(i).getInfo());
			genes.get(i).setFitness(ans);
			
			//見せるのは１０個
			if(i<10){
				System.out.print(i+" ");
				genes.get(i).printInfo();
			}
		}
		
		System.out.println();
	}
	//交叉
	public void crossover(int a,int b){
		crossover.clossover(cuurentGenes.get(a), cuurentGenes.get(b), nextGenes);
	}
	/**
	 * 適応度に比例して選択を行い、その個体の配列での番号を返す
	 * @return int
	 */
	public int roulette(){
		Random rnd = new Random();
		int max=cuurentGenes.size();
		
		double denominator=0;
		for(int i=0;i<max;i++){
			denominator+=cuurentGenes.get(i).getFitness();
		}
		int count=cuurentGenes.get(0).getFitness();
		int rndint=rnd.nextInt((int)denominator);
		for(int i=0;i<max;i++){
			if(rndint<count) return i;
			count+=cuurentGenes.get(i+1).getFitness();
		}
		return -1;
	}
	/**
	 *  突然変異
	 * @param p 確率
	 */
	public void mutation(double p){
		Random rnd=new Random();
		int a=(int)(p*10000);
		int j=0;
		for(Gene gene:nextGenes){
			int r=rnd.nextInt(10000);
			if(r<a){
				r=rnd.nextInt(MAX_INFO);
				//System.out.println("mutating "+j);
				gene.reverseInfo(r);
				gene.setFitness(knapsack.calcMaxWeight(gene.getInfo()));
			}
			j++;
		}
	}

	/**
	 * エリートの番号を返す
	 * @param genes
	 * @return
	 */
	private int searchElite(ArrayList<Gene> genes){
		//エリートを決める
		if(genes.size()==0) return -1;
		int highScore=genes.get(0).getFitness();
		int highNum=0;
		for(int i=1;i<genes.size();i++){
			if(genes.get(i).getFitness()>highScore){
				highScore=genes.get(i).getFitness();
				highNum=i;
			}
		}
		return highNum;
	}
	private int searchBad(ArrayList<Gene> genes){
		if(genes.size()==0) return -1;
		int badScore=genes.get(0).getFitness();
		int badNum=0;
		for(int i=0;i<genes.size();i++){
			if(genes.get(i).getFitness()<badScore){
				badScore=genes.get(i).getFitness();
				badNum=i;
			}
		}
		return badNum;
	}
	
	public static void main(String[] args) {
		KnapsackProblem knap=new KnapsackProblem();
		Management manage=new Management(knap);
		manage.start();
		
	}

}
