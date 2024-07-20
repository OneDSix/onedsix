# Engines Explained

*Subject to moving; this is going to be moved to the docs once that's done.*

## Compatibility

The only real concern for compatibility is displaying 3D objects in a 2D space.
This is possible, but may look weird and/or have heavy performance costs.

Conveniently enough, 2D and 2.5D sprites are the same, 3D sprites can exist in 2.5D cells, and if a 2D spite tries to
render in a 3D space it will automatically turn into a 2.5D sprite.\
The real issue for compatibility is

Below is a table containing the compatibility between different cell types and entity types.\
The top is Entities, being in 3D, 2.5D (2D sprite using Billboarding), and 2D.\
The side is Cells, only being in 2D or 3D.

The obvious winner here is 2D.

|      | 3D                     | 2.5D               | 2D                     |
|------|------------------------|--------------------|------------------------|
| 3D   | :heavy_check_mark:     | :heavy_check_mark: | :large_orange_diamond: |
| 2D   | :x:                    | :heavy_check_mark: | :heavy_check_mark:     |
