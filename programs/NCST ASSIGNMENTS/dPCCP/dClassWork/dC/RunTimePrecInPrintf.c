    #include <stdio.h>
    #include <string.h>
    #include <stdlib.h>

    int
    main ( void )
    {
        char *p;

        p = (char*) &p;
        strcpy ( p, "Hi" );
        printf ( "%s\n", &p );
        printf ( "***********");
        //return EXIT_SUCCESS;
    }


