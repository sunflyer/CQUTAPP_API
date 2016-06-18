package cn.sunflyer.cqutapi.user;

public class ScoreRecord {
	
	private String name , GPA , score , id , type , credit , nature;
	
	public ScoreRecord(String name , String GPA , String score , String id , String type , String credit , String nature){
		setName(name);
		setGPA(GPA);
		setScore(score);
		setId(id);
		setType(type);
		setCredit(credit);
		setNature(nature);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGPA() {
		return GPA;
	}

	public void setGPA(String gPA) {
		GPA = gPA;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String toString(){
		return name + "\t" + GPA + "\t" + score + "\t" + id + "\t" + type + "\t" + credit;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
}
