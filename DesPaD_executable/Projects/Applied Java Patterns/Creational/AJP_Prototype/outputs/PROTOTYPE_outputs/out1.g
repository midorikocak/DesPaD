// Subdue 5.2.2 graph in dot format

digraph SubdueGraph {
  1 [label="Interface",color=blue,fontcolor=blue];
  2 [label="Class",color=blue,fontcolor=blue];
  3 [label="Class",color=blue,fontcolor=blue];
  3 -> 1 [label="implements",color=blue,fontcolor=blue];
  2 -> 3 [label="related_with_its_method",color=blue,fontcolor=blue];
  3 -> 3 [label="has_the_return_type_of",color=blue,fontcolor=blue];
  2 -> 3 [label="creates_objects_of",color=black,fontcolor=black];
  3 -> 3 [label="creates_objects_of",color=black,fontcolor=black];
}
