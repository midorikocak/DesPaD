// Subdue 5.2.2 graph in dot format

digraph SubdueGraph {
  4 [label="Class",color=blue,fontcolor=blue];
  4 -> 4 [label="creates_objects_of",color=blue,fontcolor=blue];
  4 -> 4 [label="has_the_return_type_of",color=blue,fontcolor=blue];
  4 -> 4 [label="related_with_its_method",color=blue,fontcolor=blue];
  1 [label="Class",color=black,fontcolor=black];
  2 [label="Class",color=black,fontcolor=black];
  3 [label="Class",color=black,fontcolor=black];
  1 -> 2 [label="creates_objects_of",color=black,fontcolor=black];
  1 -> 4 [label="calls_method_of",color=black,fontcolor=black];
  2 -> 4 [label="calls_method_of",color=black,fontcolor=black];
  2 -> 2 [label="calls_method_of",color=black,fontcolor=black];
  3 -> 1 [label="related_with_its_method",color=black,fontcolor=black];
  3 -> 1 [label="creates_objects_of",color=black,fontcolor=black];
  4 -> 4 [label="has_the_field_type_of",color=black,fontcolor=black];
  4 -> 4 [label="overrides_methods_of",color=black,fontcolor=black];
  4 -> 4 [label="calls_method_of",color=black,fontcolor=black];
}
