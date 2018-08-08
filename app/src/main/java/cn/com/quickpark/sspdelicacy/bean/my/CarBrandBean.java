package cn.com.quickpark.sspdelicacy.bean.my;

import java.util.ArrayList;
import java.util.ArrayList;

/**
 * Created by y on 2018/7/23.
 */

public class CarBrandBean {

    private ArrayList<CarBrandModel> A;
    private ArrayList<CarBrandModel> B;
    private ArrayList<CarBrandModel> C;
    private ArrayList<CarBrandModel> D;
    private ArrayList<CarBrandModel> E;
    private ArrayList<CarBrandModel> F;
    private ArrayList<CarBrandModel> G;
    private ArrayList<CarBrandModel> H;
    private ArrayList<CarBrandModel> I;
    private ArrayList<CarBrandModel> J;
    private ArrayList<CarBrandModel> K;
    private ArrayList<CarBrandModel> L;
    private ArrayList<CarBrandModel> M;
    private ArrayList<CarBrandModel> N;
    private ArrayList<CarBrandModel> O;
    private ArrayList<CarBrandModel> P;
    private ArrayList<CarBrandModel> Q;
    private ArrayList<CarBrandModel> R;
    private ArrayList<CarBrandModel> S;
    private ArrayList<CarBrandModel> T;
    private ArrayList<CarBrandModel> U;
    private ArrayList<CarBrandModel> V;
    private ArrayList<CarBrandModel> W;
    private ArrayList<CarBrandModel> X;
    private ArrayList<CarBrandModel> Y;
    private ArrayList<CarBrandModel> Z;

    private ArrayList<CarBrandModel> mCarBrandModels = new ArrayList<>();


    public ArrayList<CarBrandModel> getA() {
        return A;
    }

    public void setA(ArrayList<CarBrandModel> a) {
        A = a;
    }

    public ArrayList<CarBrandModel> getB() {
        return B;
    }

    public void setB(ArrayList<CarBrandModel> b) {
        B = b;
    }

    public ArrayList<CarBrandModel> getC() {
        return C;
    }

    public void setC(ArrayList<CarBrandModel> c) {
        C = c;
    }

    public ArrayList<CarBrandModel> getD() {
        return D;
    }

    public void setD(ArrayList<CarBrandModel> d) {
        D = d;
    }

    public ArrayList<CarBrandModel> getE() {
        return E;
    }

    public void setE(ArrayList<CarBrandModel> e) {
        E = e;
    }

    public ArrayList<CarBrandModel> getF() {
        return F;
    }

    public void setF(ArrayList<CarBrandModel> f) {
        F = f;
    }

    public ArrayList<CarBrandModel> getG() {
        return G;
    }

    public void setG(ArrayList<CarBrandModel> g) {
        G = g;
    }

    public ArrayList<CarBrandModel> getH() {
        return H;
    }

    public void setH(ArrayList<CarBrandModel> h) {
        H = h;
    }

    public ArrayList<CarBrandModel> getI() {
        return I;
    }

    public void setI(ArrayList<CarBrandModel> i) {
        I = i;
    }

    public ArrayList<CarBrandModel> getJ() {
        return J;
    }

    public void setJ(ArrayList<CarBrandModel> j) {
        J = j;
    }

    public ArrayList<CarBrandModel> getK() {
        return K;
    }

    public void setK(ArrayList<CarBrandModel> k) {
        K = k;
    }

    public ArrayList<CarBrandModel> getL() {
        return L;
    }

    public void setL(ArrayList<CarBrandModel> l) {
        L = l;
    }

    public ArrayList<CarBrandModel> getM() {
        return M;
    }

    public void setM(ArrayList<CarBrandModel> m) {
        M = m;
    }

    public ArrayList<CarBrandModel> getN() {
        return N;
    }

    public void setN(ArrayList<CarBrandModel> n) {
        N = n;
    }

    public ArrayList<CarBrandModel> getO() {
        return O;
    }

    public void setO(ArrayList<CarBrandModel> o) {
        O = o;
    }

    public ArrayList<CarBrandModel> getP() {
        return P;
    }

    public void setP(ArrayList<CarBrandModel> p) {
        P = p;
    }

    public ArrayList<CarBrandModel> getQ() {
        return Q;
    }

    public void setQ(ArrayList<CarBrandModel> q) {
        Q = q;
    }

    public ArrayList<CarBrandModel> getR() {
        return R;
    }

    public void setR(ArrayList<CarBrandModel> r) {
        R = r;
    }

    public ArrayList<CarBrandModel> getS() {
        return S;
    }

    public void setS(ArrayList<CarBrandModel> s) {
        S = s;
    }

    public ArrayList<CarBrandModel> getT() {
        return T;
    }

    public void setT(ArrayList<CarBrandModel> t) {
        T = t;
    }

    public ArrayList<CarBrandModel> getU() {
        return U;
    }

    public void setU(ArrayList<CarBrandModel> u) {
        U = u;
    }

    public ArrayList<CarBrandModel> getV() {
        return V;
    }

    public void setV(ArrayList<CarBrandModel> v) {
        V = v;
    }

    public ArrayList<CarBrandModel> getW() {
        return W;
    }

    public void setW(ArrayList<CarBrandModel> w) {
        W = w;
    }

    public ArrayList<CarBrandModel> getX() {
        return X;
    }

    public void setX(ArrayList<CarBrandModel> x) {
        X = x;
    }

    public ArrayList<CarBrandModel> getY() {
        return Y;
    }

    public void setY(ArrayList<CarBrandModel> y) {
        Y = y;
    }

    public ArrayList<CarBrandModel> getZ() {
        return Z;
    }

    public void setZ(ArrayList<CarBrandModel> z) {
        Z = z;
    }

    public ArrayList<CarBrandModel> getmCarBrandModels() {
        return mCarBrandModels;
    }

    public void setmCarBrandModels(ArrayList<CarBrandModel> mCarBrandModels) {
        this.mCarBrandModels = mCarBrandModels;
    }

    public ArrayList<CarBrandModel> getCarBrandModels() {
        mCarBrandModels.clear();
        addList(A);
        addList(B);
        addList(C);
        addList(D);
        addList(E);
        addList(F);
        addList(G);
        addList(H);
        addList(I);
        addList(J);
        addList(K);
        addList(L);
        addList(M);
        addList(N);
        addList(O);
        addList(P);
        addList(Q);
        addList(R);
        addList(S);
        addList(T);
        addList(U);
        addList(V);
        addList(W);
        addList(X);
        addList(Y);
        addList(Z);
        return mCarBrandModels;
    }

    private void addList(ArrayList<CarBrandModel> carBrandModels) {
        if (carBrandModels != null && carBrandModels.size() > 0) {
            mCarBrandModels.addAll(carBrandModels);
        }
    }
}
