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
<!-- End: Javascript-->
@stack('customJS')

</body>

</html>
