<!doctype html>
<html lang="en">

<head>

    <meta charset="utf-8" />
    <title>Dashboard | Upcube - Admin & Dashboard Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
    <meta content="Themesdesign" name="author" />
    <!-- Start: Header -->
    <x-upcube.header-layout />
    <!-- End: Header -->
    @stack('customCSS')
</head>

<body data-topbar="dark">

<!-- <body data-layout="horizontal" data-topbar="dark"> -->

<!-- Begin page -->
<div id="layout-wrapper">

    <!-- Start: Navbar -->
    <x-upcube.navbar-layout />
    <!-- End: Navbar -->

    <!-- ========== Left Sidebar Start ========== -->
    <x-upcube.sidebar-layout />
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start right Content here -->
    <!-- ============================================================== -->
    <div class="main-content">

        <div class="page-content">
            <div class="container-fluid">
                {{$slot}}
            </div>

        </div>
        <!-- End Page-content -->

        <!-- Start : Footer -->
        <x-upcube.footer-layout />
        <!-- End : Footer -->

    </div>
    <!-- end main content-->

</div>
<!-- END layout-wrapper -->

<!-- Start: Javascript-->
<x-upcube.js-layout/>
@if ($message = Session::get('success'))
    <script>
        $(document).ready(function () {
            let pesan = '{!! $message !!}';
            toastr.info(pesan);
            // toastr.danger("Are you the six fingered man?");
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": true,
                "positionClass": "toast-bottom-right",
                "preventDuplicates": true,
                "onclick": null,
                "showDuration": 300,
                "hideDuration": 1000,
                "timeOut": 5000,
                "extendedTimeOut": 1000,
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };

        });
    </script>
@endif

@if ($message = Session::get('warning'))
    <script>
        $(document).ready(function () {
            let pesan = '{!! $message !!}';
            toastr.warning(pesan);
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": true,
                "positionClass": "toast-bottom-right",
                "preventDuplicates": true,
                "onclick": null,
                "showDuration": 300,
                "hideDuration": 1000,
                "timeOut": 5000,
                "extendedTimeOut": 1000,
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
        });
    </script>
@endif
@if($errors->any())
    <script>
        $(document).ready(function () {
            let pesan = '{{$errors->first()}}';
            toastr.error(pesan);
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": true,
                "positionClass": "toast-bottom-right",
                "preventDuplicates": true,
                "onclick": null,
                "showDuration": 300,
                "hideDuration": 1000,
                "timeOut": 5000,
                "extendedTimeOut": 1000,
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
        });
    </script>
@endif
<!-- End: Javascript-->
@stack('customJS')

</body>

</html>
