# Purpose of the software

## A calendar explorer

Champollion Explorer is generating a sorted fusioned list of files after exploration of several folder trees.

The software generates two lists:

* A view of all files sorted chronologically,
* A view of all files sorted by extension then by size.

Those views can be searched into in the browser.

Each view contains the list of files with, for all files:

* The link to the file,
* The link to the folder where the file is located

The generated files have no CSS, si the display is basic.

## Prerequisites

The Java version installed on the PC must be more than Java 6 or more.

```
C:\test> java --version
openjdk 11.0.17 2022-10-18
OpenJDK Runtime Environment Temurin-11.0.17+8 (build 11.0.17+8)
OpenJDK 64-Bit Server VM Temurin-11.0.17+8 (build 11.0.17+8, mixed mode)
```

## Download

Download the latest release.

## Use

Type in a console, in a `.bat` file (Windows) or `.sh` file (Linux):

```
java -jar CalendarExplorer.jar PROPERTYFILE.properties
```

## Property file

The following keys are recognized in the .properties file:

- `dir1` : Content must be a directory.
- `dir2` : Content must be a directory.
- `dir3` : Content must be a directory.
- `dir4` : Content must be a directory.
- `dir5` : Content must be a directory.
- `dir6` : Content must be a directory.
- `dir7` : Content must be a directory.
- `dir8` : Content must be a directory.
- `dir9` : Content must be a directory.
- `outputfilename` : Content must be a file (better with full path).

## Samples

Sample of `PROPERTYFILE.properties` for Windows:

```
# List of directories
dir1 = R:\\Path\\To\\Folder
dir2 = W:\\Another\\Path\\To\\Another\\Folder
#dir3 =

# Output file name
outputfolder = C:\\MyData\\MyOutputFolder
```

Sample of `PROPERTYFILE.properties` for Linux:

```
# List of directories
dir1 = /home/Path/To/Folder
dir2 = /network/Another/Path/To/Another/Folder
#dir3 =

# Output file name
outputfolder = /home/user/MyData/MyOutputFolder
```

See also the `script` folder.

