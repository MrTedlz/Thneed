package application;

public class Inventory {
int smallPink;
int smallBlack;
int smallBlue;
int smallGrey;
int medPink;
int medBlack;
int medBlue;
int medGrey;
int largePink;
int largeBlack;
int largeBlue;
int largeGrey;

public Inventory() {
	//No Contructor
}
public Inventory(int smallPink, int smallBlack, int smallBlue, int smallGrey, int medPink, int medBlack, int medBlue, int medGrey, int largePink, int largeBlack, int largeBlue, int largeGrey) {
	this.smallPink = smallPink;
	this.smallBlack = smallBlack;
	this.smallBlue = smallBlue;
	this.smallGrey = smallGrey;
	this.medPink = medPink;
	this.medBlack = medBlack;
	this.medBlue = medBlue;
	this.medGrey = medGrey;
	this.largePink = largePink;
	this.largeBlack = largeBlack;
	this.largeBlue = largeBlue;
	this.largeGrey = largeGrey;
}
//Setters
public void setSmallPink(int num) {
	smallPink = num;
}
public void setSmallBlack(int num) {
	smallBlack = num;
}
public void setSmallBlue(int num) {
	smallBlue = num;
}
public void setSmallGrey(int num) {
	smallGrey = num;
}
public void setMedPink(int num) {
	medPink = num;
}
public void setMedBlack(int num) {
	medBlack = num;
}
public void setMedBlue(int num) {
	medBlue = num;
}
public void setMedGrey(int num) {
	medGrey = num;
}
public void setLargePink(int num) {
	largePink = num;
}
public void setLargeBlack(int num) {
	largeBlack = num;
}
public void setLargeBlue(int num) {
	largeBlue = num;
}
public void setLargeGrey(int num) {
	largeGrey = num;
}


//Getters
public int getSmallPink() {
	return smallPink;
}
public int getSmallBlack() {
	return smallBlack;
}
public int getSmallBlue() {
	return smallBlue;
}
public int getSmallGrey() {
	return smallGrey;
}
public int getMedPink() {
	return medPink;
}
public int getMedBlack() {
	return medBlack;
}
public int getMedBlue() {
	return medBlue;
}
public int getMedGrey() {
	return medGrey;
}
public int getLargePink() {
	return largePink;
}
public int getLargeBlack() {
	return largeBlack;
}
public int getLargeBlue() {
	return largeBlue;
}
public int getlargeGrey() {
	return largeGrey;
}


}
