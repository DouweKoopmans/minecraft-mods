package kes5219.utils.misc;

public class Triple<T1, T2, T3>
{
  public final T1 first;
  public final T2 second;
  public final T3 third;
  
  public Triple(T1 value1, T2 value2, T3 value3)
  {
    this.first = value1;
    this.second = value2;
    this.third = value3;
  }
  
  public Triple(T1 value1, T2 value2)
  {
    this.first = value1;
    this.second = value2;
    this.third = null;
  }
}
