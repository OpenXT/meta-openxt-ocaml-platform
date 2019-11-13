open Unix

let list_dir path = 
    try
        let hnd = Unix.opendir path in
        let rec entries () =
            try
                match Unix.readdir hnd with
                | "." | ".." -> entries ()
                | e          -> e :: entries ()
            with End_of_file -> []
        in
            ignore (List.map print_endline (List.map (Filename.concat path) (entries ())));
            Unix.closedir hnd
    with _ -> print_endline "Failed"

let () =
    if Array.length Sys.argv = 1 then
        list_dir "./"
    else
        for i = 1 to Array.length Sys.argv - 1 do
            list_dir Sys.argv.(i)
        done
