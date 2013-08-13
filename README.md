MeshSomething
=============

Taken from 'Shape Measures for Triangles' by Gerald Farin

We compare a variety of triangle shape measures using concepts such as smoothness and convexity. We show that one of
these measures, the elongation measure, lends itself to an intuitive geometric interpretation.

When triangulating a mesh of traingles one wants to avoid 'slivers' i.e., triangles which are either very obtuse or very acute.

These notions can be made more precise by the introduction of exact shape measures. Many such shape measures exist for triangles, all formalizing (in different ways) the notion of being close to equilateral. We will consider (with T denoting a triangle)
. maxangle(T): T’s largest angle,
. minangle(T): T’s smallest angle,
. aspectratio(T): ratio of radius of T’s circumcircle to
that of its incircle,
. elongation(T): the elongation of T’s Steiner circumellipse,
. inverse-mean-ratio(T): an eigenvalue-based measure
of T’s deviation from being equilateral.