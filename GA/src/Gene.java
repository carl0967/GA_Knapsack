import java.util.Random;

/**
 * 遺伝子
 * 配列infoのある要素が0のとき、その品物を入れない。1の時品物を入れる。という遺伝子情報を持っている。
 * @author info
 *
 */
public class Gene implements Cloneable{
	private int info[]; //所持データ
	private int max;
	private int fitness=0; //適応値
	
	public Gene(int max){
		this.max=max;
		info=new int[max];
		
		Random rnd = new Random();
		for(int i=0;i<max;i++){

	        info[i] = rnd.nextInt(2);
		}
	}
	public void setInfo(int[] tmpInfo){
		this.info=tmpInfo.clone();
		//this.fitness=KnapsackProblem.calcMaxWeight(info);
		
	}
	public int[] getInfo(){
		return info;
	}
	public void setFitness(int fitness){
		this.fitness=fitness;
	}
	public int getFitness(){
		return fitness;
	}
	public int getSizeOfInfo(){
		return max;
	}
	public void changeGene(int start,int end,Gene gene){
		int[] tmpInfo=gene.getInfo();
		for(int i=start;i<end;i++){
			info[i]=tmpInfo[i];
		}
	}
	public void printInfo(){
		for(int i=0;i<max;i++){
			System.out.print(info[i]+",");
		}
		System.out.println(" "+fitness);
	}
	public void reverseInfo(int p){
		if(info[p]==0) info[p]=1;
		else if(info[p]==1) info[p]=0;
	}
	
    public Object clone() {
        try {
            Gene cloneGene = (Gene) super.clone();
            cloneGene.setInfo(this.getInfo()); 
            cloneGene.setFitness(this.getFitness());
            return cloneGene;
        } catch (CloneNotSupportedException e) {
        	return null;
        }
    }
}
