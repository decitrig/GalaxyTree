package net.decitrig.util;

public class Pair<A, B> {
  private final A head;
  private final B tail;

  public static <A, B> Pair<A, B> of(A head, B tail) {
    return new Pair<A, B>(head, tail);
  }

  public Pair(A head, B tail) {
    this.head = head;
    this.tail = tail;
  }

  public A head() {
    return head;
  }

  public B tail() {
    return tail;
  }
}
