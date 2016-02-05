// Subdue 5.2.2 graph in dot format

digraph SubdueGraph {
  4 [label="Interface",color=blue,fontcolor=blue];
  7 [label="Class",color=blue,fontcolor=blue];
  9 [label="Class",color=blue,fontcolor=blue];
  10 [label="Interface",color=blue,fontcolor=blue];
  9 -> 4 [label="implements",color=blue,fontcolor=blue];
  7 -> 10 [label="has_a_method_with_the_return_type_of",color=blue,fontcolor=blue];
  7 -> 9 [label="calls_method_of",color=blue,fontcolor=blue];
  1 [label="Class",color=black,fontcolor=black];
  2 [label="Class",color=black,fontcolor=black];
  3 [label="Class",color=black,fontcolor=black];
  5 [label="Interface",color=black,fontcolor=black];
  6 [label="Class",color=black,fontcolor=black];
  8 [label="Interface",color=black,fontcolor=black];
  11 [label="Class",color=black,fontcolor=black];
  12 [label="Class",color=black,fontcolor=black];
  13 [label="Class",color=black,fontcolor=black];
  14 [label="Class",color=black,fontcolor=black];
  15 [label="Class",color=black,fontcolor=black];
  16 [label="Class",color=black,fontcolor=black];
  17 [label="Class",color=black,fontcolor=black];
  18 [label="Class",color=black,fontcolor=black];
  19 [label="Class",color=black,fontcolor=black];
  20 [label="Class",color=black,fontcolor=black];
  21 [label="Class",color=black,fontcolor=black];
  22 [label="Class",color=black,fontcolor=black];
  23 [label="Class",color=black,fontcolor=black];
  24 [label="Class",color=black,fontcolor=black];
  25 [label="Class",color=black,fontcolor=black];
  26 [label="Class",color=black,fontcolor=black];
  27 [label="Class",color=black,fontcolor=black];
  28 [label="Class",color=black,fontcolor=black];
  29 [label="Class",color=black,fontcolor=black];
  30 [label="Class",color=black,fontcolor=black];
  31 [label="Class",color=black,fontcolor=black];
  32 [label="Class",color=black,fontcolor=black];
  33 [label="Class",color=black,fontcolor=black];
  34 [label="Class",color=black,fontcolor=black];
  35 [label="Class",color=black,fontcolor=black];
  1 -> 5 [label="implements",color=black,fontcolor=black];
  4 -> 8 [label="extends",color=black,fontcolor=black];
  5 -> 8 [label="extends",color=black,fontcolor=black];
  8 -> 10 [label="extends",color=black,fontcolor=black];
  1 -> 11 [label="has_the_field_type_of",color=black,fontcolor=black];
  1 -> 11 [label="creates_objects_of",color=black,fontcolor=black];
  1 -> 1 [label="overrides_methods_of",color=black,fontcolor=black];
  1 -> 4 [label="related_with_its_method",color=black,fontcolor=black];
  1 -> 4 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  1 -> 12 [label="calls_method_of",color=black,fontcolor=black];
  1 -> 11 [label="calls_method_of",color=black,fontcolor=black];
  1 -> 13 [label="related_with_its_method",color=black,fontcolor=black];
  1 -> 13 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  1 -> 14 [label="calls_method_of",color=black,fontcolor=black];
  1 -> 15 [label="related_with_its_method",color=black,fontcolor=black];
  1 -> 15 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  1 -> 16 [label="related_with_its_method",color=black,fontcolor=black];
  1 -> 16 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  2 -> 2 [label="overrides_methods_of",color=black,fontcolor=black];
  2 -> 4 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 4 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  2 -> 17 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 17 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  2 -> 15 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 12 [label="calls_method_of",color=black,fontcolor=black];
  2 -> 18 [label="calls_method_of",color=black,fontcolor=black];
  2 -> 5 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 5 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  2 -> 19 [label="calls_method_of",color=black,fontcolor=black];
  2 -> 8 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 8 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  2 -> 20 [label="calls_method_of",color=black,fontcolor=black];
  2 -> 21 [label="related_with_its_method",color=black,fontcolor=black];
  2 -> 22 [label="calls_method_of",color=black,fontcolor=black];
  3 -> 3 [label="overrides_methods_of",color=black,fontcolor=black];
  3 -> 16 [label="related_with_its_method",color=black,fontcolor=black];
  3 -> 16 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  3 -> 23 [label="creates_objects_of",color=black,fontcolor=black];
  3 -> 5 [label="related_with_its_method",color=black,fontcolor=black];
  4 -> 16 [label="related_with_its_method",color=black,fontcolor=black];
  4 -> 16 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  4 -> 13 [label="related_with_its_method",color=black,fontcolor=black];
  4 -> 13 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  4 -> 13 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  4 -> 16 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  5 -> 4 [label="related_with_its_method",color=black,fontcolor=black];
  5 -> 4 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  5 -> 13 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  5 -> 13 [label="related_with_its_method",color=black,fontcolor=black];
  6 -> 21 [label="related_with_its_method",color=black,fontcolor=black];
  6 -> 21 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  6 -> 6 [label="overrides_methods_of",color=black,fontcolor=black];
  6 -> 16 [label="related_with_its_method",color=black,fontcolor=black];
  6 -> 16 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  6 -> 23 [label="related_with_its_method",color=black,fontcolor=black];
  6 -> 23 [label="creates_objects_of",color=black,fontcolor=black];
  6 -> 25 [label="calls_method_of",color=black,fontcolor=black];
  6 -> 23 [label="calls_method_of",color=black,fontcolor=black];
  6 -> 26 [label="related_with_its_method",color=black,fontcolor=black];
  6 -> 26 [label="creates_objects_of",color=black,fontcolor=black];
  6 -> 27 [label="creates_objects_of",color=black,fontcolor=black];
  6 -> 28 [label="calls_method_of",color=black,fontcolor=black];
  6 -> 27 [label="calls_method_of",color=black,fontcolor=black];
  6 -> 29 [label="calls_method_of",color=black,fontcolor=black];
  7 -> 16 [label="has_the_field_type_of",color=black,fontcolor=black];
  7 -> 3 [label="overrides_methods_of",color=black,fontcolor=black];
  7 -> 16 [label="related_with_its_method",color=black,fontcolor=black];
  7 -> 16 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  7 -> 16 [label="related_with_its_method",color=black,fontcolor=black];
  7 -> 7 [label="overrides_methods_of",color=black,fontcolor=black];
  7 -> 16 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  7 -> 29 [label="calls_method_of",color=black,fontcolor=black];
  7 -> 10 [label="related_with_its_method",color=black,fontcolor=black];
  7 -> 5 [label="related_with_its_method",color=black,fontcolor=black];
  7 -> 1 [label="creates_objects_of",color=black,fontcolor=black];
  7 -> 4 [label="related_with_its_method",color=black,fontcolor=black];
  7 -> 9 [label="creates_objects_of",color=black,fontcolor=black];
  7 -> 30 [label="calls_method_of",color=black,fontcolor=black];
  7 -> 10 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  7 -> 31 [label="related_with_its_method",color=black,fontcolor=black];
  7 -> 31 [label="creates_objects_of",color=black,fontcolor=black];
  7 -> 32 [label="creates_objects_of",color=black,fontcolor=black];
  7 -> 33 [label="calls_method_of",color=black,fontcolor=black];
  7 -> 32 [label="calls_method_of",color=black,fontcolor=black];
  8 -> 15 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  8 -> 15 [label="related_with_its_method",color=black,fontcolor=black];
  9 -> 16 [label="has_the_field_type_of",color=black,fontcolor=black];
  9 -> 34 [label="has_the_field_type_of",color=black,fontcolor=black];
  9 -> 34 [label="creates_objects_of",color=black,fontcolor=black];
  9 -> 1 [label="overrides_methods_of",color=black,fontcolor=black];
  9 -> 16 [label="related_with_its_method",color=black,fontcolor=black];
  9 -> 16 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  9 -> 34 [label="calls_method_of",color=black,fontcolor=black];
  9 -> 35 [label="calls_method_of",color=black,fontcolor=black];
  9 -> 13 [label="related_with_its_method",color=black,fontcolor=black];
  9 -> 13 [label="has_a_method_that_has_an_input_parameter_with_the_type_of",color=black,fontcolor=black];
  9 -> 13 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  9 -> 15 [label="related_with_its_method",color=black,fontcolor=black];
  9 -> 15 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  9 -> 16 [label="has_a_method_with_the_return_type_of",color=black,fontcolor=black];
  9 -> 9 [label="overrides_methods_of",color=black,fontcolor=black];
}