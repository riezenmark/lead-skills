package org.omaewa.notastepik.util;

public final class Views {
    public interface Id { }
    public interface Representation { }
    public interface IdRepresentation extends Id, Representation { }
    public interface Review extends Id { }
    public interface Lesson extends Id { }
}
