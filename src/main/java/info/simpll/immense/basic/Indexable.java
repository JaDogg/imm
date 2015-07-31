package info.simpll.immense.basic;


public interface Indexable<T> {

    public T get(int index);

    public boolean has(int index);

    public int size();
}
