// Subdue 5.2.2 graph in dot format

digraph SubdueGraph {
  1 [label="Class",color=blue,fontcolor=blue];
  3 [label="Interface",color=blue,fontcolor=blue];
  5 [label="Class",color=blue,fontcolor=blue];
  6 [label="Class",color=blue,fontcolor=blue];
  5 -> 1 [label="extends",color=blue,fontcolor=blue];
  6 -> 3 [label="implements",color=blue,fontcolor=blue];
  1 -> 3 [label="related_with_its_method",color=blue,fontcolor=blue];
  2 [label="Class",color=black,fontcolor=black];
  4 [label="Class",color=black,fontcolor=black];
  4 -> 1 [label="extends",color=black,fontcolor=black];
  1 -> 3 [label="has_the_field_type_of",color=black,fontcolor=black];
  1 -> 3 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  2 -> 3 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 6 [label="creates_objects_of",color=black,fontcolor=black];
  2 -> 1 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 1 [label="creates_objects_of",color=black,fontcolor=black];
  2 -> 1 [label="calls_method_of",color=black,fontcolor=black];
  2 -> 4 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 4 [label="creates_objects_of",color=black,fontcolor=black];
  2 -> 5 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 5 [label="creates_objects_of",color=black,fontcolor=black];
  4 -> 1 [label="overrides_methods_of",color=black,fontcolor=black];
  5 -> 1 [label="overrides_methods_of",color=black,fontcolor=black];
}
